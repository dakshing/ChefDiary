package com.mydiaries.chefdiary.recipe.services;

import com.mydiaries.chefdiary.recipe.exceptions.InvalidInputException;
import com.mydiaries.chefdiary.recipe.exceptions.RecipeNotFoundException;
import com.mydiaries.chefdiary.recipe.interfaces.IngredientDTO;
import com.mydiaries.chefdiary.recipe.interfaces.RecipeDTO;
import com.mydiaries.chefdiary.recipe.interfaces.RecipeDetails;
import com.mydiaries.chefdiary.recipe.model.Ingredient;
import com.mydiaries.chefdiary.recipe.model.Recipe;
import com.mydiaries.chefdiary.recipe.model.RecipeIngredientPK;
import com.mydiaries.chefdiary.recipe.model.RecipeToIngredients;
import com.mydiaries.chefdiary.recipe.repository.IngredientRepository;
import com.mydiaries.chefdiary.recipe.repository.RecipeRepository;
import com.mydiaries.chefdiary.recipe.repository.RecipeToIngredientRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final RecipeToIngredientRepository recipeToIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper, EntityManager entityManager, RecipeToIngredientRepository recipeToIngredientRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
        Converter<Set<RecipeToIngredients>, Set<IngredientDTO>> ingredientsDTOConverter = new AbstractConverter<>() {
            @Override
            protected Set<IngredientDTO> convert(Set<RecipeToIngredients> recipeToIngredients) {
                if (recipeToIngredients == null) {
                    return null;
                }
                Set<IngredientDTO> returnSet = new HashSet<>();
                for (var recipeToIngredient: recipeToIngredients) {
                    Ingredient ingredientDetails = recipeToIngredient.getIngredient();
                    IngredientDTO ingredientDTO = modelMapper.map(ingredientDetails, IngredientDTO.class);
                    modelMapper.map(recipeToIngredient, ingredientDTO);
                    if (ingredientDTO.getUnit() == null || ingredientDTO.getUnit().equals("")) {
                        ingredientDTO.setUnit(ingredientDetails.getDefaultUnit());
                    }
                    //TODO Use the name changer instead
                    ingredientDTO.setIndex(recipeToIngredient.getPosition());
                    returnSet.add(ingredientDTO);
                }
                return returnSet;
            }
        };
        this.modelMapper.typeMap(Recipe.class, RecipeDTO.class).addMappings(mapper -> mapper.using(ingredientsDTOConverter).map(Recipe::getIngredients, RecipeDTO::setIngredients));
        this.modelMapper.typeMap(RecipeDTO.class, Recipe.class).setPropertyCondition(Conditions.isNotNull());
        this.modelMapper.typeMap(IngredientDTO.class, Ingredient.class).setPropertyCondition(Conditions.isNotNull());
        this.modelMapper.typeMap(IngredientDTO.class, RecipeToIngredients.class).setPropertyCondition(Conditions.isNotNull());

        this.entityManager = entityManager;
        this.recipeToIngredientRepository = recipeToIngredientRepository;
    }

    @Override
    public List<RecipeDetails> getRecipesForListView() {
        return recipeRepository.findAllBy();
    }

    @Override
    @Transactional
    public Recipe saveRecipeDetails(RecipeDTO recipeDTO) {
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
        recipe.setIngredients(null);
        Set<IngredientDTO> ingredients = recipeDTO.getIngredients();
        recipeRepository.save(recipe);
        Long recipeId = recipe.getId();
        for (var ingredientDTO: ingredients) {
            Ingredient ingredient = modelMapper.map(ingredientDTO, Ingredient.class);
            ingredientRepository.save(ingredient);
            RecipeToIngredients mapping = modelMapper.map(ingredientDTO, RecipeToIngredients.class);
            mapping.setPosition(ingredientDTO.getIndex());
            mapping.setRecipeId(recipeId);
            mapping.setIngredientId(ingredient.getId());
            recipeToIngredientRepository.save(mapping);
        }
        return recipe;
    }

    @Override
    public Recipe getRecipeByID(Long recipeId) throws RecipeNotFoundException {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }
        return recipe.get();
    }

    @Override
    public RecipeDTO convertRecipeToDTO(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDTO.class);
    }

    @Override
    public void deleteRecipe(Long id) throws RecipeNotFoundException {
        Recipe recipe = getRecipeByID(id);
        recipeRepository.delete(recipe);
    }

    @Override
    @Transactional
    public Recipe editRecipe(Recipe oldRecipe, RecipeDTO recipeDTO) {
        modelMapper.map(recipeDTO, oldRecipe);
        recipeRepository.save(oldRecipe);
        Long recipeId = oldRecipe.getId();
        Set<IngredientDTO> ingredientDTOS = recipeDTO.getIngredients();
        Set<RecipeToIngredients> oldIngredients = oldRecipe.getIngredients();
        if (ingredientDTOS == null) {
            return oldRecipe;
        }
        for (var ingredientDTO: ingredientDTOS) {
            Long ingredientId = ingredientDTO.getId();
            Ingredient ingredientToUpdate = null;
            RecipeToIngredients mapping = null;
            if (ingredientId == null) {
                ingredientToUpdate = modelMapper.map(ingredientDTO, Ingredient.class);
                mapping = modelMapper.map(ingredientDTO, RecipeToIngredients.class);
                mapping.setPosition(ingredientDTO.getIndex());
                mapping.setRecipeId(recipeId);
            }
            else {
                boolean exists = false;
                for (RecipeToIngredients oldIngredient : oldIngredients) {
                    if (oldIngredient.getIngredientId().equals(ingredientId)) {
                        ingredientToUpdate = oldIngredient.getIngredient();
                        modelMapper.map(ingredientDTO, ingredientToUpdate);
                        mapping = recipeToIngredientRepository.findById(new RecipeIngredientPK(recipeId, ingredientId)).orElseThrow();
                        modelMapper.map(ingredientDTO, mapping);
                        if (ingredientDTO.getIndex() != 0L) {
                            mapping.setPosition(ingredientDTO.getIndex());
                        }
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    throw new InvalidInputException();
                }
            }
            ingredientRepository.save(ingredientToUpdate);
            mapping.setIngredientId(ingredientToUpdate.getId());
            recipeToIngredientRepository.save(mapping);
        }

        for (RecipeToIngredients oldIngredient : oldIngredients) {
            Long ingredientID = oldIngredient.getIngredientId();
            boolean exists = false;
            for (IngredientDTO ingredientDTO : ingredientDTOS) {
                if (ingredientID.equals(ingredientDTO.getId())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                entityManager.remove(oldIngredient);
            }
        }

        return oldRecipe;
    }

    @Override
    @Transactional
    public void refresh(Recipe savedRecipe) {
        entityManager.refresh(savedRecipe);
        for (var ingredient: savedRecipe.getIngredients()) {
            entityManager.refresh(ingredient);
        }
    }
}

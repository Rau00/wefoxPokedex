package technical.test.wefoxpokedex.utils.view

import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.data.model.source.PokemonType
import technical.test.wefoxpokedex.data.model.view.PokemonModelView

class ConvertModelDataToModelView {

    companion object {

        fun dataToViewModel(pokemonData: PokemonModel): PokemonModelView {
            return PokemonModelView(
                name = pokemonData.name,
                weight = pokemonData.weight.toString(),
                height = pokemonData.height.toString(),
                sprite = pokemonData.sprites.front_default,
                dateCatched = pokemonData.dateCatched,
                baseExperience = pokemonData.baseExperience.toString(),
                types = getTypes(pokemonData.types)
            )
        }

        private fun getTypes(PokemonTypes: List<PokemonType>): List<String>{
            val listType = mutableListOf<String>()
            for (type in PokemonTypes) {
                listType.add(type.type.name)
            }
            return listType
        }

        fun dataToViewModelList(pokemonDataList: List<PokemonModel>):MutableList<PokemonModelView> {
            val pokemonView = mutableListOf<PokemonModelView>()
            for (pokemon in pokemonDataList) {
                pokemonView.add(ConvertModelDataToModelView.dataToViewModel(pokemon))
            }
            return pokemonView
        }
    }
}
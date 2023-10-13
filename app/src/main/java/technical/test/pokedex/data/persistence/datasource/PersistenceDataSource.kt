package technical.test.pokedex.data.persistence.datasource

import technical.test.pokedex.data.model.source.PokemonModel

interface PersistenceDataSource {
    suspend fun getPokemonsCatched(): List<PokemonModel>
    suspend fun storePokemonCatched(pokemon: PokemonModel)
    suspend fun setFreePokemonCatched(pokemonId: Int)
    suspend fun setFreeAllPokemon()

}
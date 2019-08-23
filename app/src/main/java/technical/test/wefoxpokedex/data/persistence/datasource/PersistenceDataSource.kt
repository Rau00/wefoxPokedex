package technical.test.wefoxpokedex.data.persistence.datasource

import technical.test.wefoxpokedex.data.model.source.PokemonModel

interface PersistenceDataSource {
    suspend fun getPokemonsCatched(): List<PokemonModel>
    suspend fun storePokemonCatched(pokemon: PokemonModel)
    suspend fun setFreePokemonCatched(pokemonId: Int)
    suspend fun setFreeAllPokemon()

}
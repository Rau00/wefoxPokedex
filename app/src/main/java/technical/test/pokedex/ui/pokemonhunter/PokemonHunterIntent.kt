package technical.test.pokedex.ui.pokemonhunter

sealed class PokemonHunterIntent {
    data object SearchPokemon : PokemonHunterIntent()
    data object CatchPokemon : PokemonHunterIntent()
}
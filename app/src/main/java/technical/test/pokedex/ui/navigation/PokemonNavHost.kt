package technical.test.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import technical.test.pokedex.ui.backpack.BackpackView
import technical.test.pokedex.ui.backpack.BackpackViewModel
import technical.test.pokedex.ui.pokemondetail.PokemonDetailView
import technical.test.pokedex.ui.pokemondetail.PokemonDetailsViewModel
import technical.test.pokedex.ui.pokemonhunter.PokemonHunterView
import technical.test.pokedex.ui.pokemonhunter.PokemonHunterViewModel

@Composable
fun PokemonNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Route = Backpack,
    backpackViewModel: BackpackViewModel = hiltViewModel(),
    pokemonHunterViewModel: PokemonHunterViewModel = hiltViewModel(),
    pokemonDetailViewModel: PokemonDetailsViewModel = hiltViewModel()
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Backpack> {
            BackpackView(backpackViewModel,
                navigateToDetail = {pokemonId ->  navController.navigate(PokemonDetail(pokemonId)) },
                navigateToHunting = { navController.navigate(PokemonHunter) })
        }
        composable<PokemonHunter> {
            PokemonHunterView(pokemonHunterViewModel) {
                navController.popBackStack()
            }
        }
        composable<PokemonDetail> { backStackEntry ->
            val pokemonId = backStackEntry.toRoute<PokemonDetail>().pokemonId
            val pokemon = pokemonDetailViewModel.getPokemonDetails(pokemonId.toString())
            pokemon?.let { PokemonDetailView(it) }
        }
    }
}
package technical.test.pokedex.ui.pokemonhunter.view

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import technical.test.pokedex.R
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.components.Loading
import technical.test.pokedex.ui.pokemonhunter.viewmodel.PokemonHunterViewModel

@Composable
fun PokemonHunterView(viewModel: PokemonHunterViewModel = hiltViewModel()) {

    val pokemonFound by viewModel.pokemonFound.collectAsState()
    val pokemonCaught = viewModel.isPokemonCaught.collectAsState()

    val activity = LocalContext.current as Activity

    when (pokemonFound) {
        PokemonViewStates.Idle -> {
            viewModel.searchPokemon()
        }

        is PokemonViewStates.ErrorDataFound -> {
            ErrorDataFound((pokemonFound as PokemonViewStates.ErrorDataFound).errorMessage) {
                viewModel.searchPokemon()
            }
        }

        PokemonViewStates.Loading -> {
            Loading()
        }

        is PokemonViewStates.PokemonFounded -> {
            PokemonFounded((pokemonFound as PokemonViewStates.PokemonFounded).pokemonFunded,
                viewModel.isCaught,
                catchAction = { viewModel.catchPokemon() },
                leaveAction = { activity.finish() },
                searchAction = { viewModel.searchPokemon() })
        }

        else -> {}
    }


}

@Composable
private fun PokemonFounded(
    pokemon: PokemonModel,
    pokemonCaught: Boolean,
    catchAction: () -> Unit,
    leaveAction: () -> Unit,
    searchAction: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.margin_md)),
            model = pokemon.sprite,
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.margin_md)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "${stringResource(id = R.string.hunt_weight)} ${pokemon.weight}")
            Text(text = pokemon.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = "${stringResource(id = R.string.hunt_height)} ${pokemon.height}")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.margin_md)),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            if (pokemonCaught) {
                Button(
                    onClick = { searchAction() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(id = R.string.hunt_Search))
                }

            } else {
                Button(
                    onClick = { catchAction() },
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pokeball),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin_md)))
            Button(
                onClick = { leaveAction() },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(id = R.string.hunt_leave))
            }
        }
    }
}

@Composable
private fun ErrorDataFound(errorMessage: String, buttonAction: () -> Unit) {

    Column {
        Text(text = errorMessage)
        Button(
            onClick = { buttonAction() },
            modifier = Modifier.weight(1f)
        ) {
            Text(stringResource(id = R.string.hunt_Search))
        }
    }

}
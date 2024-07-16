package technical.test.pokedex.ui.pokemondetail

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import technical.test.pokedex.R
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.components.PokemonStatsView

@Composable
fun PokemonDetailView(pokemon: PokemonModel) {

    val activity = LocalContext.current as Activity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = pokemon.sprite, contentDescription = null
        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(dimensionResource(id = R.dimen.margin_md))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${stringResource(id = R.string.detail_weight)}: ${pokemon.weight}",
                    )
                    Text(
                        text = "${pokemon.name}(${pokemon.order})",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    )
                    Text(
                        text = "${stringResource(id = R.string.detail_height)}: ${pokemon.height}",
                    )
                }
            }

            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_md)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_s))
            ) {

                Text(
                    text = "${stringResource(id = R.string.detail_catched)}: ${pokemon.dateCaught}",
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "${stringResource(id = R.string.generation)}: ${pokemon.calculateGeneration()}",
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "${stringResource(id = R.string.win_probability)}: ${pokemon.getVictoryProbability(activity)}",
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "${stringResource(id = R.string.detail_experience)}: ${pokemon.baseExperience}",
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.margin_s))
                ) {
                    Text(
                        text = "${stringResource(id = R.string.detail_type)}:",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    pokemon.types.forEach { type ->
                        Text(text = type)
                    }
                }
                PokemonStatsView(pokemonStats = pokemon.stats)
            }

        }
    }
}

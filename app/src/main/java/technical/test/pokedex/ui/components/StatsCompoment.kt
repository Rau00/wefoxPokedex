package technical.test.pokedex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import technical.test.pokedex.R
import technical.test.pokedex.domain.models.PokemonStats

@Composable
fun PokemonStatsView(pokemonStats: List<PokemonStats>) {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_md)),
        horizontalAlignment = Alignment.Start
    ) {
        pokemonStats.forEach {
            Text(text = "${it.name}: ${it.baseStat}")
        }
    }
}
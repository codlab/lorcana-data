package eu.codlab.lorcana

import eu.codlab.lorcana.abilities.Ability
import eu.codlab.lorcana.resources.Resources
import eu.codlab.lorcana.utils.AbstractLoader
import eu.codlab.lorcana.utils.Provider
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

object Abilities : AbstractLoader<Map<String, Ability>>(
    Provider.json,
    Resources.files.abilities_json,
    "abilities_json",
    MapSerializer(String.serializer(), Ability.serializer())
)

package eu.codlab.lorcana

import eu.codlab.lorcana.franchises.Franchise
import eu.codlab.lorcana.resources.Resources
import eu.codlab.lorcana.utils.AbstractLoader
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

object Franchises : AbstractLoader<Map<String, Franchise>>(
    Resources.files.franchises_yaml,
    "franchises_yaml",
    MapSerializer(String.serializer(), Franchise.serializer())
)

package ru.bezdar.bank.plugin.common

import java.util.Properties

class SortedProperties : Properties() {
    override val entries: MutableSet<MutableMap.MutableEntry<Any, Any>>
        get() = super.entries.sortedBy { it.key.toString() }.toMutableSet()
}
package de.wvvh.stayhomeapp.util


infix fun <T> Array<T>.unorderedEquals(other: Array<T>): Boolean {
    return this.toList() unorderedEquals other.toList()
}

infix fun <T> List<T>.unorderedEquals(other: List<T>): Boolean {
    if (this.size != other.size) {
        return false
    }

    // Check if every elem from other has exactly on occurrence in this
    val foundIndices = mutableListOf<Int>()
    for (elem in other) {
        val i = this.indexOf(elem)
        if (i == -1 || foundIndices.contains(i)) {
            return false
        } else {
            foundIndices.add(i)
        }
    }

    return true
}
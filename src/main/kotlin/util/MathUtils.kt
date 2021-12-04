package util

import java.math.BigInteger
import java.security.MessageDigest

object MathUtils {

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    /**
     * Generates all permutations described in your question
     * For the sake of performance it calls [onNextPermutation] for each permutation,
     * but it uses the same list to write permutations in,
     * so if you need to use these permutations elsewhere copy its parameter by youself
     */
    fun <T> generatePermutations(elementsList: List<T>, onNextPermutation: (List<T>) -> Unit) {
        if (elementsList.isEmpty()) {
            onNextPermutation(emptyList())
            return
        }
        val elementCounts = LinkedHashMap<T, Int>() // We need to remember order in which the elements were added to map
        elementsList.forEach {
            elementCounts[it] = 1 + (elementCounts[it] ?: 0) // Count our elements
        }
        val differentElements = elementCounts.keys

        val totalPermutationsCount = elementCounts.values.fold(1) { a, b -> a * b }

        // Next 3 collections are reused through generator loop for the sake of performance

        val takenEntryNumbers = LinkedHashMap<T, Int>() // Number of entry of each element we will take to next permutation
        differentElements.forEach { takenEntryNumbers[it] = 0 }

        val entriesOfElementViewed = HashMap<T, Int>() // Count of entries of each element we already viewed while iterating elementsList
        differentElements.forEach { entriesOfElementViewed[it] = 0 }

        val currentPermutation = ArrayList<T>() // Mutable list which we will use to write permutations in
        repeat(differentElements.size) { currentPermutation.add(elementsList[0]) } // Just fill it to needed size

        repeat(totalPermutationsCount) { // Generate next permutation
            var entriesTaken = 0 // Total count of entries taken in this permutation
            for (element in elementsList) { // Generate current permutation
                if (entriesOfElementViewed[element] == takenEntryNumbers[element]) {
                    currentPermutation[entriesTaken++] = element
                }
                entriesOfElementViewed[element] = 1 + (entriesOfElementViewed[element] ?: 0)
            }
            onNextPermutation(currentPermutation)
            // Update collections to start next permutation
            differentElements.forEach { entriesOfElementViewed[it] = 0 }
            // Generate next permutation of entry numbers, where each entry number is less than element's total count
            for (element in differentElements) {
                if (1 + (takenEntryNumbers[element] ?: 0) == elementCounts[element]) {
                    takenEntryNumbers[element] = 0
                }
                else {
                    takenEntryNumbers[element] = 1 + (takenEntryNumbers[element] ?: 0)
                    break
                }
            }

        }

    }
}
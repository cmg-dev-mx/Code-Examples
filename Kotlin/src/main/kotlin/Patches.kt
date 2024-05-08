import java.io.IOException

fun validatePatches(width: Int, height: Int, data: Array<IntArray>): Int {
    if ((data.size < height) 
    || (data.filter { it.size != width }).isNotEmpty()) {
        throw IOException("Required data is not well formatted")
    }

    val patches = hashMapOf<List<Int>, Int>()
    var patchCounter = 0

    for (rowLine in data.indices) {
        for (cellNumber in 0 until data[rowLine].size) {

            if (data[rowLine][cellNumber] == 1) {
                if (rowLine > 0) {
                    if (data[rowLine - 1][cellNumber] == 1) {
                        patches[listOf(rowLine, cellNumber)] = patches[listOf(rowLine-1, cellNumber)]!!
                    }
                }
                if (cellNumber > 0) {
                    if (data[rowLine][cellNumber - 1] == 1) {
                        if (patches.containsKey(listOf(rowLine, cellNumber))
                            && patches[listOf(rowLine, cellNumber - 1)] != patches[listOf(
                                rowLine,
                                cellNumber
                            )]
                        ) {
                            patches.filter { (_, value) ->
                                value == patches[listOf(rowLine, cellNumber - 1)]
                            }.forEach { (key, _) ->
                                patches[key] = patches[listOf(rowLine, cellNumber)]!!
                            }
                        } else {
                            patches[listOf(rowLine, cellNumber)] = patches[listOf(rowLine, cellNumber -1)]!!
                        }
                    }
                }
                if (!patches.containsKey(listOf(rowLine, cellNumber))) {
                    patches[listOf(rowLine, cellNumber)] = patchCounter
                    patchCounter++
                }
            }
        }
    }

    return patches.values.distinct().size
}
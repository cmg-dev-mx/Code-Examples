import java.util.*

fun kthLargestElement(arr: Array<Int>, k: Int): Int {
    val maxIntHeap = MaxIntHeap(arrayOf())
    arr.forEach { maxIntHeap.add(it) }

    println(Arrays.toString(maxIntHeap.nodes))

    for (i in 0 until k - 1) {
        println("Next to peak: ${maxIntHeap.peek()}")
        maxIntHeap.poll()
        println(Arrays.toString(maxIntHeap.nodes))
    }

    println("Base: ${maxIntHeap.peek()}")

    return maxIntHeap.peek()
}

private class MaxIntHeap(var nodes: Array<Int> = arrayOf()) {

    private var size = 0
    private var capacity = nodes.size

    fun peek(): Int {
        if (size == 0) throw IllegalStateException()
        return nodes[0]
    }

    fun poll(): Int {
        if (size == 0) throw IllegalStateException()
        val node = nodes[0]
        nodes[0] = nodes[size - 1]
        size--
        heapifyDown()
        return node
    }

    fun add(item: Int) {
        ensureExtraCapacity()
        nodes[size] = item
        size++
        heapifyUp()
    }

    private fun heapifyUp() {
        var index = size - 1
        while (hasParent(index) && parent(index) < nodes[index]) {
            swap(getParentIndex(index), index)
            index = getParentIndex(index)
        }
    }

    private fun heapifyDown() {
        var index = 0
        while (hasLeftChild(index)) {
            var biggerChildIndex = getLeftChildIndex(index)
            if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                biggerChildIndex = getRightChildIndex(index)
            }

            if (nodes[index] > nodes[biggerChildIndex]) {
                break
            } else {
                swap(index, biggerChildIndex)
            }
            index = biggerChildIndex
        }
    }

    private fun getLeftChildIndex(parentIndex: Int) = (2 * parentIndex) + 1
    private fun getRightChildIndex(parentIndex: Int) = (2 * parentIndex) + 2
    private fun getParentIndex(childIndex: Int) = (childIndex - 1) / 2

    private fun hasLeftChild(index: Int) = getLeftChildIndex(index) < size
    private fun hasRightChild(index: Int) = getRightChildIndex(index) < size
    private fun hasParent(index: Int) = getParentIndex(index) >= 0

    private fun leftChild(index: Int) = nodes[getLeftChildIndex(index)]
    private fun rightChild(index: Int) = nodes[getRightChildIndex(index)]
    private fun parent(index: Int) = nodes[getParentIndex(index)]

    private fun swap(origin: Int, destiny: Int) {
        val temp = nodes[origin]
        nodes[origin] = nodes[destiny]
        nodes[destiny] = temp
    }

    private fun ensureExtraCapacity() {
        if (size == capacity) {
            if (size == 0) {
                capacity = 1
                nodes = Arrays.copyOf(nodes, capacity)
            } else {
                nodes = Arrays.copyOf(nodes, capacity * 2)
                capacity *= 2
            }
        }
    }
}

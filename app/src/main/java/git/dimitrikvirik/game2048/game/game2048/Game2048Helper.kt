package git.dimitrikvirik.game2048.game.game2048


fun <T : Any> List<T?>.moveAndMergeEqual(double: (T) -> T): List<T> {

    val nonNulls = this.filterNotNull().toMutableList()

    with(nonNulls) {
        var cnt = 1
        while(cnt < this.size){
            if (this[cnt] == this[cnt - 1]) {
                this[cnt - 1] = double(this[cnt])
                this.removeAt(cnt)
            }
            ++cnt
        }
    }

    return nonNulls
}


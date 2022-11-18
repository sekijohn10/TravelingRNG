package edu.byui.joh18113.travelingrng

class Deck {
    var deck: ArrayDeque<Card> = buildDeck()
    fun reset() {
        deck.clear()
        deck.addAll(buildDeck())
    }

    fun shuffle() {
        //TODO
        deck.shuffle()
    }

    fun drawCard(): Card {
        return deck.removeFirst()
    }

    private fun buildDeck(): ArrayDeque<Card> {
        val list: ArrayDeque<Card> = ArrayDeque()
        var x = 1
        while (x < 53) {
            list.add(Card(x))
            x++
        }
        //TODO
        list.shuffle()
        return list
    }
}
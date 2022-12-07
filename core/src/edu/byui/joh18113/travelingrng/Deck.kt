package edu.byui.joh18113.travelingrng

class Deck {
    private var deck: ArrayDeque<Card> = buildDeck()
    fun reset() {
        deck.clear()
        deck.addAll(buildDeck())
    }

    fun shuffle() {
        //TODO
        deck.shuffle()
    }

    fun isEmpty(): Boolean {
        return deck.isEmpty()
    }

    fun drawCard(): Card {
        return deck.removeFirst()
    }

    private fun buildDeck(): ArrayDeque<Card> {
        val list: ArrayDeque<Card> = ArrayDeque()
        val club = "Club_"
        val heart = "Heart_"
        val diamond = "Diamond_"
        val spade = "Spade_"
        var count = 0
        val royals: ArrayList<String> = ArrayList()
        royals.add("Ace")
        royals.add("Queen")
        royals.add("King")
        royals.add("Jack")
        while (count < 11) {
            if (count < 4) {
                list.add(Card(club + royals.get(count)))
                list.add(Card(heart + royals.get(count)))
                list.add(Card(diamond + royals.get(count)))
                list.add(Card(spade + royals.get(count)))
            }
            if (count > 1) {
                list.add(Card(club + count))
                list.add(Card(heart + count))
                list.add(Card(diamond + count))
                list.add(Card(spade + count))
            }
            count++
        }
        list.shuffle()
        return list
    }
}
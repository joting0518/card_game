import random

class Card:
    # 初始化 card, suit and rank是兩個元素
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def getsuit(self):  # getter
        return self.suit

    def getrank(self):  # getter
        return self.rank

def deal_cards(num_players, num_cards):
    suits = ["s", "h", "d", "c"]
    ranks = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"]

    #完整的撲克牌
    deck = [Card(suit, rank) for suit in suits for rank in ranks]

    #洗牌效果
    random.shuffle(deck)

    #發牌
    players_hands = [[] for _ in range(num_players)]#儲存玩家的牌們
    for _ in range(num_cards):
        for i, player_hand in enumerate(players_hands):
            player_hand.append(deck.pop())

    return players_hands

def score(cards):
    Suit = []
    Ranknum = []
    Score = 0

    # Get rank and suit from card
    for card in cards:
        rank = card.getrank()
        if rank.isnumeric():
            Ranknum.append(int(rank))
        else:
            Ranknum.append(rank)
        Suit.append(card.getsuit())

    # samenumber list
    samenumber = [0] * 13
    for y in Ranknum:
        if isinstance(y, int):
            samenumber[y - 1] += 1

    # Copy samenumber for adjustment
    Samenumber = list(samenumber)

    # sameicon
    # 判斷有沒有五個花色相同
    c = 0
    samesuit = 0
    if all(Suit[c] == s for s in Suit[c:c+5]):
        samesuit = 1
    else:
        samesuit = 0

    # start
    # 找出最多相同數字的組合（22233) -> 2有三個，故maxN=3
    maxN = samenumber[0]
    for ktr in range(len(samenumber)):
        if samenumber[ktr] >= maxN:
            maxN = samenumber[ktr]

    # 成對數
    twocount = sum(1 for k in samenumber if k == 2)

    # 考慮Q、K、A、2、3 這種組合
    isOrder = True
    order = [0] * 18
    for number in Ranknum:
        if isinstance(number, int):
            for x in range(1, 14):
                if number == x:
                    order[x] = 1

            if number == 1:
                order[14] = 1
            if number == 2:
                order[15] = 1
            if number == 3:
                order[16] = 1
            if number == 4:
                order[17] = 1

    # 判斷是否連續
    for u in range(1, 13):
        count = sum(order[u:u+6])
        if count == 5:
            isOrder = True
            break
        else:
            isOrder = False

    if maxN == 1:
        if isOrder:
            if samesuit == 1:
                # continue and samesuit
                Score += 100
            else:
                # continue
                Score += 5
        elif not isOrder:
            if 1 in Ranknum:
                # single but A
                Score += 1
    elif maxN == 2:
        if twocount == 2 and samenumber[0] == 2:
            Score += 5
            # 2+2+A
        elif twocount == 2:
            Score += 4
            # 2+2+1
        elif samenumber[0] == 1:
            Score += 3
            # 2+1+1+A
        else:
            Score += 2
            # 2+1+1+1
    elif maxN == 3:
        if 2 in Samenumber:
            Score += 10
            # 3+2
        elif samenumber[0] > 0:
            Score += 3
            # 3+1+A AAA+1+1
        else:
            Score += 2
            # 3+1+1
    elif maxN == 4:
        if samenumber[0] == 1:
            Score += 21
            # 4+A
        else:
            Score += 20
            # 4+1
    elif samesuit == 1:
        Score += 3
        # 五張花色相同

    return Score

def main():
    try:
        num_players = int(input("請輸入玩家數量: "))
        num_cards = int(input("請輸入每位玩家的卡牌數量: "))

        players_hands = deal_cards(num_players, num_cards)
        
        #結果 印出卡牌＆分數
        for i, player_hand in enumerate(players_hands, start=1):
            print(f"\n玩家 {i} 的卡牌:")
            for card in player_hand:
                print(f"{card.getsuit()}{card.getrank()}", end=" ")

            player_score = score(player_hand)
            print(f"\n玩家 {i} 的分數: {player_score}")

    except Exception as e:
        # 處理輸入錯誤
        print("輸入錯誤，遊戲退出。")
        print(e)
        exit(1)  # 非正常退出

main()

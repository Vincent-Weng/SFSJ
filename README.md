# SFSJ

## Naming conventions

Rules in the game usually have no official English translation. Here is a table
of the mapping used in the codebase. **Class, variable and method names may
include the English representations. In the UI, Chinese expressions should
always be preferred**.

|Chinese|English|Notes|
|:------|:------|:----|
|整局/比赛(指四人从2起游戏至游戏终止的一局)|Playing|
|局/盘(指一次发4副牌至全部盘出完的一次对局)|Game|
|轮(四人轮流出牌一次)|Round|
|叫主|Claim (DominantSuit)|
|庄家|Banker|
|庄家方|Defender|
|抢分方|Attacker|
|主牌|Dominants|
|副牌|Non-Dominants|
|主牌花色|DominantSuit|
|主牌级数|DominantRank|
|毙牌|Override|
|(发牌时的)底牌|HiddenCards|
|(庄家扣下的)底牌|TreasureCards|
|翘底|RobTreasure|
|一搭|Slice|如一张5,一对5,三个5|
|牌型|Pattern|可以为单个Slice,也可以为多个Slice的组合(Combo)|


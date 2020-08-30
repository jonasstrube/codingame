import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

# player_count: the amount of players (2 to 4)
# my_id: my player ID (0, 1, 2 or 3)
# zone_count: the amount of zones on the map
# link_count: the amount of links between all zones
player_count, my_id, zone_count, link_count = [int(i) for i in input().split()]
zones = [[0] * 2 for i in range(zone_count)]
links = [[0] * 2 for i in range(link_count)]
print("ZONES", file=sys.stderr, flush=True)
print("zone_id | platinum_source", file=sys.stderr, flush=True)
for i in range(zone_count):
    zone_id, platinum_source = [int(j) for j in input().split()]
    zones[i][0] = zone_id           # zone_id: this zone's ID (between 0 and zoneCount-1)
    zones[i][1] = platinum_source   # platinum_source: the amount of Platinum this zone can provide per game turn
    print(str(zones[i][0]) + " | " + str(zones[i][1]), file=sys.stderr, flush=True)
print("LINKS", file=sys.stderr, flush=True)
print("zone_1 | zone_2", file=sys.stderr, flush=True)
for i in range(link_count):
    zone_1, zone_2 = [int(j) for j in input().split()]
    links[i][0] = zone_1
    links[i][1] = zone_2
    print(str(links[i][0]) + " | " + str(links[i][1]), file=sys.stderr, flush=True)

# game loop
zone_occupancy = [[0] * 6 for i in range(zone_count)]
while True:
    platinum = int(input())  # my available Platinum
    print("ZONE_OCCUPANCY", file=sys.stderr, flush=True)
    print("z_id | owner_id | pods_p0 | pods_p1 | pods_p2 | pods_p3", file=sys.stderr, flush=True)
    for i in range(zone_count):
        z_id, owner_id, pods_p0, pods_p1, pods_p2, pods_p3 = [int(j) for j in input().split()]
        zone_occupancy[i][0] = z_id         # z_id: this zone's ID
        zone_occupancy[i][1] = owner_id     # owner_id: the player who owns this zone (-1 otherwise)
        zone_occupancy[i][2] = pods_p0      # pods_p0: player 0's PODs on this zone
        zone_occupancy[i][3] = pods_p1      # pods_p1: player 1's PODs on this zone
        zone_occupancy[i][4] = pods_p2      # pods_p2: player 2's PODs on this zone (always 0 for a two player game)
        zone_occupancy[i][5] = pods_p3      # pods_p3: player 3's PODs on this zone (always 0 for a two or three player game)
        print(str(zone_occupancy[i][0]) + " | " + str(zone_occupancy[i][1]) + " | " + str(zone_occupancy[i][2]) + " | " + str(zone_occupancy[i][3]) + " | " + str(zone_occupancy[i][4]) + " | " + str(zone_occupancy[i][5]) + " | " + str(zone_occupancy[i][6]), file=sys.stderr, flush=True)

    # Write an action using print


    # first line for movement commands, second line for POD purchase (see the protocol in the statement for details)
    print("WAIT")
    print("1 73")

# Reclaim-the-Iron-Throne
Flow Graph, Max Flow Ford Fulkerson Algorithm using time efficient data structure and Object-Oriented principles.
# About project
Rhaenyra Targaryen, the first-born child of King Viserys I Targaryen, was considered the
rightful heir to the Iron Throne. She was going to rule all the Westeros but her half-brother,
Aegon Targaryen, seized the throne by force.  <br/>
Rhaenyra is now determined to reclaim the Iron Throne from Aegon. However, she
heard from her consultants that Aegon started to gather support from all corners of Westeros to maintain his power. She knows that in order to defeat Aegon, she needs to prevent
him from gathering enough support. So she trusts your algorithm skills and asks you to help
her take her rightful place as queen. <br/>
Aegon’s main purpose is to construct the biggest army with soldiers from all the regions in King’s Landing. He began by sending envoys to The Reach, Riverlands, Dorne,
The Vale of Arryn, The Westerlands, and The Stormlands, offering alliances and promises of
rewards in exchange for support (e.g. troops). He is pleased to receive enthusiastic responses
from these 6 regions and begins to mobilize his forces. <br/>
The regions are willing to send all their troops to King’s Landing but because of limited
transportation resources, there are a limited amount of troops that can reach the King’s
Landing. But Aegon and his consultants know how to maximize the number of troops that
can come to King’s Landing to support him. <br/>
For Rhaenyra to defeat Aegon, she must first know the size of the troops he’s gathering in King’s Landing. Also, she wants to block the route of all the troops, so needs to
know where to send her armies to. Your duty is to give this crucial information to Rhaenyra.  <br/>

# Details
• There are exactly 6 regions willing to send all the troops they have. <br/>
• Each troop travels to King’s Landing and each troop has the same number of soldiers.  <br/>
• There are many cities in the routes of the troops and only a limited number of troops
can travel between two cities due to transportation limitations and harsh conditions.
Luckily, we know the capacity of each road. <br/>
• If there is a road from one city to another, let’s say from city 1 to city 2, it doesn’t
mean that there is also a road from 2 to 1. <br/>
• A region is most vulnerable when preparing to send all the troops it has and a road
is most vulnerable when the troops are traveling at the maximum capacity of that
road. Rheanyra wants to send her armies to the vulnerable regions and roads. She also
wants to block all the troops going to King’s Landing, so you must find the cut with
full capacities that add up to the maximum number of troops.  <br/>
• There are no roads from cities to regions or from King’s Landing to cities/regions. <br/>

# Input
• The first line represents the total number of cities except King’s Landing.<br/>
• The second line contains 6 integers, each representing the number of troops
a region is willing to send.<br/>
• The next 6 lines will give the ID of a region, the number of troops they are
trying to send, the IDs of the cities that are reachable from the given region,
and the capacity of the road from that region to that city.<br/>
• Each next line will give the ID of a city, the IDs of the cities that are
reachable from the given city, and the capacity of the road between these
two cities.<br/>

# Output 
• An integer, the maximum number of troops that can reach the King’s Landing.<br/>
• Regions and roads (as pairs of vertices line by line) that Rhaenyra
must send her armies to.
<br/>

<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="Water" tilewidth="16" tileheight="16" tilecount="4" columns="4">
 <image source="Water.png" width="64" height="16"/>
 <tile id="0">
  <properties>
   <property name="Collidable" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="1">
  <properties>
   <property name="Collidable" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="2">
  <properties>
   <property name="Collidable" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="3">
  <properties>
   <property name="Collidable" type="bool" value="true"/>
  </properties>
 </tile>
 <wangsets>
  <wangset name="Random water" type="mixed" tile="-1">
   <wangcolor name="" color="#ff0000" tile="-1" probability="1"/>
   <wangtile tileid="0" wangid="1,1,1,1,1,1,1,1"/>
   <wangtile tileid="1" wangid="1,1,1,1,1,1,1,1"/>
   <wangtile tileid="2" wangid="1,1,1,1,1,1,1,1"/>
   <wangtile tileid="3" wangid="1,1,1,1,1,1,1,1"/>
  </wangset>
 </wangsets>
</tileset>

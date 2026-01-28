### How to build:

```bash
# this should be enough, but you can make an issue if there is any problem. I know that "it works on my computer" is not always a good answer
mvn clean install
```

### How do I update the plugin at each minecraft version?

- I first update this fork with main official branch of decenholograms: https://github.com/Guarmanda/DecentHolograms
- then, in fall_effect folder, I create a copy of the latest version of FallEffect class from the previous version, and I adapt it to the new version (most of the time, nothing changes, or only one thing)
You can watch this commit for example (only the 4 first files): https://github.com/Guarmanda/LootChest/commit/b421f9f61d75d8f4f2fd21ccecca0a21a83e474c


<dl class="customResourceFieldmc_versions">
<dt>Tested Minecraft Versions:</dt>
<dd>1.7 - 1.21.X</dd>
</dl>
<dl class="customResourceFieldlanguages">
<dt>Languages Supported:</dt>
<dd>Editable language file</dd>
</dl>
<dl class="customResourceFielddonate_link">
<dt>Donation Link:</dt>
<dd><a href="https://www.paypal.me/BlackEyes99" rel="nofollow" class="externalLink" target="_blank">https://www.paypal.me/BlackEyes99</a></dd>
</dl>
<div style="text-align: center"><span style="font-size: 26px">LootChest</span></div><br />
<span style="font-size: 12px">Configurable chest reloader<br />
</span><br />
<span style="font-size: 22px"><b>Features:</b>
</span>&#8203;

- Set particles for each chest (35 particles supported) (can be disabled for each chest)<br />
- BungeeCord messages on chest respawn/take<br />
- Timer on chest&#039;s hologram (can be disabled)<br />
- Give a chest content to a player (it allow you to create a kind of kit, since essentials kits doesn&#039;t store nbt tags)<br />
- 1.7 to 1.21.1 support (No holograms in 1.7)</span><br />
- Menu to create or edit everything<br />
- Editable inventory for each chest<br />
- Editable item chance for each item in each chest (default: 100% (editable))<br />
- Editable respawn time for each chest<br />
- Editable hologram for each chest (can be disabled for each chest)<br />
- Chest is deleted when empty (but will still respawn at defined time) (can be disabled)<br />
- Editable lang file, editable menu names<br />
- Editable general particle&#039;s speed, number, spawn rate and radius (can be disabled)<br />
- Make a copy of another lootChest by creating a chest then selecting the chest to copy in the editing menu<br />
- Broadcast on chest respawn (can be disabled for each chest)<br />
- Change a chest&#039;s position<br />
- Automatic config and lang update , so that you don&#039;t have to delete anything when you update this plugin<br />
- Random spawn within a radius around location where chest was created, or around a random player (editable radius for each chest) (can be disabled)<br />
- Awesome fall effect (fully editable) (can be disabled for each chest)<br />
- Broadcast on chest taken (can be disabled for each chest)<br />

<span style="font-size: 12px"><b><span style="font-size: 22px">Commands:
</span></b></span>&#8203;</div>

<span style="font-size: 12px"><span style="font-size: 15px">-/lc create &lt;name&gt; : Creates a chest and opens creating menu</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc edit &lt;name&gt; : Open editing menu</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc help : Guess it</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc respawn &lt;name&gt; : respawn a chest</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc respawnall : respawn all chests</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc remove &lt;name&gt; : removes the given chest</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc setholo &lt;name&gt; &lt;text&gt; : set hologram of given chest. Setting holo to &quot;&quot; or &quot; &quot; or null will delete the holo</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc reload : reloads all chests</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc list : well... sorry to not have added that earlier</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc give &lt;player&gt; &lt;chest&gt; Allows u to give a chest&#039;s content to a player</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc setpos &lt;name&gt; : changes the position of a chest</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc settime &lt;name&gt; &lt;seconds&gt; : sets the respawn time of the chest without using the time menu</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc randomspawn &lt;name&gt; &lt;radius&gt; : sets the respawn radius of a chest</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc tp &lt;name&gt; : teleports you to a chest</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc togglefall &lt;name&gt;: enable/disable fall effect</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc getname : get name of targeted chest</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">-/lc locate : gives locations of all chests that haves natural respawn message enabled</span></span><br />
<div style="text-align: center"><br />
<b><span style="font-size: 22px">Permissions:</span></b><br />
<span style="font-size: 12px"><span style="font-size: 15px">for all commands: lootchest.&lt;command&gt;</span></span><br />
<span style="font-size: 12px"><span style="font-size: 15px">for admins: lootchest.admin/lootchest.*</span></span><br />
<br />

<span style="font-size: 12px"><span style="font-size: 22px"><span style="font-size: 15px"><b><span style="font-size: 22px">More infos:</span></b></span></span></span><br />
<span style="font-size: 12px"><span style="font-size: 22px"><span style="font-size: 15px">Mail: <span class="__cf_email__" data-cfemail="93e5f2fff6fde7fafdd3f4fae1fcf7bdf5e1">valentin@girod.fr</span></span></span></span><br />
<span style="font-size: 12px"><span style="font-size: 22px"><span style="font-size: 15px">Discord: Black_Eyes#5538</span></span></span><br />


<span style="font-size: 22px"><b>Using my code:</b></span><br />
<span style="font-size: 15px">Don&#039;t sell copy of this thing, it will always stay open source. You can still use my code if you want to edit this plugin for yourself, or someone in needs, or you can use some function for your own plugin^^ (the fall effect class could be useful to some, and there&#039;s many menu in this, and also some config file functions) </span>&#8203;
</div>




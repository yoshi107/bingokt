package bingo.bingo

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class Bingo : JavaPlugin(), Listener {
    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        server.pluginManager.registerEvents(this, this)
        logger.info("あっ///らめぇ～///")
    }
    var game = false
    var Items = mutableListOf<Material>()
    var RedInv = Bukkit.createInventory(null, 27, "RED TEAM SHEET")
    var BlueInv = Bukkit.createInventory(null, 27, "BLUE TEAM SHEET")
    var sec: Int = 0

    var bs3: Material? = null
    var bs4: Material? = null
    var bs5: Material? = null
    var bs12: Material? = null
    var bs13: Material? = null
    var bs14: Material? = null
    var bs21: Material? = null
    var bs22: Material? = null
    var bs23: Material? = null
    var rs3: Material? = null
    var rs4: Material? = null
    var rs5: Material? = null
    var rs12: Material? = null
    var rs13: Material? = null
    var rs14: Material? = null
    var rs21: Material? = null
    var rs22: Material? = null
    var rs23: Material? = null


    @EventHandler
    fun onLogin(event: PlayerJoinEvent){
        val p = event.player
        p.sendMessage("アイテム収集してビンゴするゲームだよん")
        val menuStar = ItemStack(Material.NETHER_STAR)
        p.inventory.addItem(menuStar)
        if(game){//途中参加
            if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.size!! >= Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.size!!){
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.addEntry(p.name)
            } else {
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.addEntry(p.name)
            }
        }

    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name == "bingo") {// bingo
            if (args[0] == "timer") {//  bingo timer
                var min = args[1].toInt()
                sec = min * 60
                sender.sendMessage("時間制限は" + min + "分に設定されました。")
            }else if (args[0] == "start") {
                val scoreboard = Bukkit.getScoreboardManager()?.mainScoreboard
                Bukkit.getScoreboardManager()?.mainScoreboard?.registerNewTeam("red")
                Bukkit.getScoreboardManager()?.mainScoreboard?.registerNewTeam("blue")
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.prefix = "§c[RED]"
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.prefix = "§b[BLUE]"

                val onlineList = mutableListOf(Bukkit.getOnlinePlayers())
                Collections.shuffle(onlineList)
                for (p in onlineList) {
                    if (scoreboard?.getTeam("red")?.size!! >= scoreboard.getTeam("blue")?.size!!) {
                        scoreboard.getTeam("blue")?.addEntry(p.toString())
                    } else {
                        scoreboard.getTeam("red")?.addEntry(p.toString())
                    }
                }
                Items.add(Material.AIR)
                Items.add(Material.APPLE)
                Items.add(Material.CAKE)
                Items.add(Material.REDSTONE_BLOCK)
                Items.add(Material.TINTED_GLASS)
                Items.add(Material.BOOKSHELF)
                Items.add(Material.OBSIDIAN)
                Items.add(Material.MAGMA_BLOCK)
                Items.add(Material.ENDER_PEARL)
                Items.add(Material.BEE_NEST)
                Items.add(Material.ANVIL)
                Items.add(Material.TNT)
                Items.add(Material.LADDER)
                Items.add(Material.PISTON)
                Items.add(Material.ENCHANTING_TABLE)
                Items.add(Material.POWERED_RAIL)
                Items.add(Material.GOLDEN_APPLE)
                Items.add(Material.SNOWBALL)
                Items.add(Material.NETHERITE_INGOT)
                Items.add(Material.STONE)
                val RInvSize = mutableListOf<Material>()
                while (10 >= rcount) {
                    RInvSize.clear()
                    RInvSize.add(Material.AIR)
                    val indexr = Random.nextInt(Items.size)
                    val resultr = Items[indexr]
                    for (rI in RedInv) {
                        if (rI != null) {
                            val RImat = rI.type
                            RInvSize.add(RImat)
                        }
                    }
                    val rduplicateCheck = RInvSize.indexOf(resultr)
                    if (rduplicateCheck == -1) {
                        when (rcount) {
                            1 -> {
                                RedInv.setItem(3, ItemStack(resultr))
                                rcount++
                            }

                            2 -> {
                                RedInv.setItem(4, ItemStack(resultr))
                                rcount++
                            }

                            3 -> {
                                RedInv.setItem(5, ItemStack(resultr))
                                rcount++
                            }

                            4 -> {
                                RedInv.setItem(12, ItemStack(resultr))
                                rcount++
                            }

                            5 -> {
                                RedInv.setItem(13, ItemStack(resultr))
                                rcount++
                            }

                            6 -> {
                                RedInv.setItem(14, ItemStack(resultr))
                                rcount++
                            }

                            7 -> {
                                RedInv.setItem(21, ItemStack(resultr))
                                rcount++
                            }

                            8 -> {
                                RedInv.setItem(22, ItemStack(resultr))
                                rcount++
                            }

                            9 -> {
                                RedInv.setItem(23, ItemStack(resultr))
                                rcount += 100
                            }
                        }
                    }
                }
                rcount = 1
                val BInvSize = mutableListOf<Material>()
                while (10 >= bcount) {
                    BInvSize.clear()
                    BInvSize.add(Material.AIR)
                    val indexb = Random.nextInt(Items.size)
                    val resultb = Items[indexb]
                    for (bI in BlueInv) {
                        if (bI != null) {
                            val BImat = bI.type
                            BInvSize.add(BImat)
                        }
                    }
                    val bduplicateCheck = BInvSize.indexOf(resultb)
                    if (bduplicateCheck == -1) {
                        when (bcount) {
                            1 -> {
                                BlueInv.setItem(3, ItemStack(resultb))
                                bcount++
                            }

                            2 -> {
                                BlueInv.setItem(4, ItemStack(resultb))
                                bcount++
                            }

                            3 -> {
                                BlueInv.setItem(5, ItemStack(resultb))
                                bcount++
                            }

                            4 -> {
                                BlueInv.setItem(12, ItemStack(resultb))
                                bcount++
                            }

                            5 -> {
                                BlueInv.setItem(13, ItemStack(resultb))
                                bcount++
                            }

                            6 -> {
                                BlueInv.setItem(14, ItemStack(resultb))
                                bcount++
                            }

                            7 -> {
                                BlueInv.setItem(21, ItemStack(resultb))
                                bcount++
                            }

                            8 -> {
                                BlueInv.setItem(22, ItemStack(resultb))
                                bcount++
                            }

                            9 -> {
                                BlueInv.setItem(23, ItemStack(resultb))
                                bcount = bcount + 100
                            }

                            else -> {}
                        }
                    }
                }
                val s = ItemStack(Material.EMERALD)
                val m = s.itemMeta
                m?.setDisplayName(ChatColor.GREEN.toString() + "BINGO!")
                s.itemMeta = m
                RedInv.setItem(16, ItemStack(s))
                BlueInv.setItem(16, ItemStack(s))
                bcount = 1
                //timer
                var waitcount = 11
                sec += 1
                object : BukkitRunnable() {
                    override fun run() {
                        waitcount--
                        sec--
//                        if (!game) {
//                            Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.unregister()
//                            Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.unregister()
//                            cancel()
//                        }
                        when (waitcount) {
                            10 -> {
                                Bukkit.broadcastMessage("開始まで...10秒前")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            5 -> {
                                Bukkit.broadcastMessage("5秒前...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            4 -> {
                                Bukkit.broadcastMessage("4秒前...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            3 -> {
                                Bukkit.broadcastMessage("3秒前...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            2 -> {
                                Bukkit.broadcastMessage("2秒前...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            1 -> {
                                Bukkit.broadcastMessage("1秒前...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            0 -> {
                                game = true
                                Bukkit.broadcastMessage("開始！！")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_BELL, 30f, 7f)
                                }
                            }

                            else -> {}
                        }
                        when (sec) {
                            301 -> {
                                Bukkit.broadcastMessage("残り5分です。")
                            }

                            181 -> {
                                Bukkit.broadcastMessage("残り3分です。")
                            }

                            61 -> {
                                Bukkit.broadcastMessage("残り1分です。")
                            }

                            31 -> {
                                Bukkit.broadcastMessage("残り30秒!!")
                            }

                            11 -> {
                                Bukkit.broadcastMessage("残り10秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            10 -> {
                                Bukkit.broadcastMessage("残り9秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            9 -> {
                                Bukkit.broadcastMessage("残り8秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            8 -> {
                                Bukkit.broadcastMessage("残り7秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            7 -> {
                                Bukkit.broadcastMessage("残り6秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            6 -> {
                                Bukkit.broadcastMessage("残り5秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            5 -> {
                                Bukkit.broadcastMessage("残り4秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            4 -> {
                                Bukkit.broadcastMessage("残り3秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            3 -> {
                                Bukkit.broadcastMessage("残り2秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            2 -> {
                                Bukkit.broadcastMessage("残り1秒...")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HAT, 30f, 14f)
                                }
                            }

                            1 -> {
                                game = false
                                Bukkit.broadcastMessage("時間切れです^^")
                                for (p in server.onlinePlayers) {
                                    p.world.playSound(p.location, Sound.ENTITY_ENDER_DRAGON_GROWL, 5f, 14f)
                                }
                                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.unregister()
                                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.unregister()
                                cancel()
                            }
                        }
                    }
                }.runTaskTimer(this, 0L, 20L)
            }
            if (args[0] == "reload") {
                reloadConfig()
            }
            if (args[0] == "end" && game) {
                game = false
                Bukkit.broadcastMessage("管理者によってゲームが終了させられました；；")
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.unregister()
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.unregister()
            }else{
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.unregister()
                Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.unregister()
            }
            if (args[0] == "switch") { // /bingo switch teamname ID
                when (args[1]) {
                    "red" -> {
                        Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.removeEntry(args[2])
                        Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.addEntry(args[2])
                    }
                    "blue" -> {
                        Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.removeEntry(args[2])
                        Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.addEntry(args[2])
                    }
                }
            }
        }
        return false
    }

    fun onRedWin() {
        for (p in Bukkit.getOnlinePlayers()) {
            if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.hasEntry(p.name) == true) {
                p.getLocation().world?.spawnEntity(p.location, EntityType.FIREWORK)
                p.world.playSound(p.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 2f)
                p.sendTitle("Victory", "Royale", 1, 100, 20)
            } else if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.hasEntry(p.name) == true) {
                p.world.playSound(p.location, Sound.ENTITY_ENDER_DRAGON_DEATH, 1f, 1f)
                p.sendTitle("YOU LOSE", "^_^;", 1, 100, 20)
            }
            Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.unregister()
            Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.unregister()
        }
    }
    fun onBlueWin() {
        for (p in Bukkit.getOnlinePlayers()) {
            if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.hasEntry(p.name) == true) {
                p.getLocation().world?.spawnEntity(p.location, EntityType.FIREWORK)
                p.world.playSound(p.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 2f)
                p.sendTitle("Victory", "Royale", 1, 100, 20)
            } else if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.hasEntry(p.name) == true) {
                p.world.playSound(p.location, Sound.ENTITY_ENDER_DRAGON_DEATH, 1f, 1f)
                p.sendTitle("YOU LOSE", "^_^;", 1, 100, 20)
            }
            Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.unregister()
            Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.unregister()
        }
    }

    @EventHandler
    fun onPickup(event: EntityPickupItemEvent) {
        if(game){
            val pickitem = event.item.itemStack.type
            val p = event.entity.name
            if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.hasEntry(p) == true){
                val rtis = mutableListOf<Material>()
                var rti = RedInv
                rs3 = rti.getItem(3)?.type
                rs4 = rti.getItem(4)?.type
                rs5 = rti.getItem(5)?.type
                rs12 = rti.getItem(12)?.type
                rs13 = rti.getItem(13)?.type
                rs14 = rti.getItem(14)?.type
                rs21 = rti.getItem(21)?.type
                rs22 = rti.getItem(22)?.type
                rs23 = rti.getItem(23)?.type
                rtis.add(rs3!!)
                rtis.add(rs4!!)
                rtis.add(rs5!!)
                rtis.add(rs12!!)
                rtis.add(rs13!!)
                rtis.add(rs14!!)
                rtis.add(rs21!!)
                rtis.add(rs22!!)
                rtis.add(rs23!!)
                val rtsi = rtis.indexOf(pickitem)
                if(rtsi > -1){
                    val rclear: Int
                    when(rtsi) {
                        0 -> {
                            rclear = 3
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        1 -> {
                            rclear = 4
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        2 -> {
                            rclear = 5
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        3 -> {
                            rclear = 12
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        4 -> {
                            rclear = 13
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        5 -> {
                            rclear = 14
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        6 -> {
                            rclear = 21
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        7 -> {
                            rclear = 22
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                        8 -> {
                            rclear = 23
                            RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                        }
                    }
                }
            } else if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.hasEntry(p) == true) {
                val btis: MutableList<Material> = ArrayList()
                var bti = BlueInv
                bs3 = bti.getItem(3)?.type
                bs4 = bti.getItem(4)?.type
                bs5 = bti.getItem(5)?.type
                bs12 = bti.getItem(12)?.type
                bs13 = bti.getItem(13)?.type
                bs14 = bti.getItem(14)?.type
                bs21 = bti.getItem(21)?.type
                bs22 = bti.getItem(22)?.type
                bs23 = bti.getItem(23)?.type
                btis.add(bs3!!)
                btis.add(bs4!!)
                btis.add(bs5!!)
                btis.add(bs12!!)
                btis.add(bs13!!)
                btis.add(bs14!!)
                btis.add(bs21!!)
                btis.add(bs22!!)
                btis.add(bs23!!)
                var btsi = btis.indexOf(pickitem)
                if(btsi != -1){
                    val bclear: Int
                    when (btsi) {
                        0 -> {
                            //Bukkit.broadcastMessage("左上");
                            bclear = 3
                            //Bukkit.broadcastMessage(this.BlueInv.getItem(bclear) + "が入っています");
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        1 -> {
                            bclear = 4
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        2 -> {
                            bclear = 5
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        3 -> {
                            bclear = 12
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        4 -> {
                            bclear = 13
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        5 -> {
                            bclear = 14
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        6 -> {
                            bclear = 21
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        7 -> {
                            bclear = 22
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                        8 -> {
                            bclear = 23
                            BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                        }
                    }
                }
            }
        }
    }



    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val handItem = event.item?.type
        if (game && handItem == Material.NETHER_STAR) {
            val p = event.player
            if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.hasEntry(p.name) == true) {
                p.openInventory(RedInv)
            } else if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.hasEntry(p.name) == true) {
                p.openInventory(BlueInv)
            }
        }
    }
    @EventHandler
    fun onInventory(event: InventoryClickEvent) {
        val InvName = event.view.title
        val slotnum = event.slot
        if (InvName == "RED TEAM SHEET") {
            event.isCancelled = true
            if (slotnum == 16) {
                if (rs3 == Material.NETHER_STAR && rs4 == Material.NETHER_STAR && rs5 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                } else if (rs12 == Material.NETHER_STAR && rs13 == Material.NETHER_STAR && rs14 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                } else if (rs21 == Material.NETHER_STAR && rs22 == Material.NETHER_STAR && rs23 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                } else if (rs3 == Material.NETHER_STAR && rs13 == Material.NETHER_STAR && rs23 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                } else if (rs5 == Material.NETHER_STAR && rs13 == Material.NETHER_STAR && rs21 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                } else if (rs3 == Material.NETHER_STAR && rs12 == Material.NETHER_STAR && rs21 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                } else if (rs4 == Material.NETHER_STAR && rs13 == Material.NETHER_STAR && rs22 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                } else if (rs5 == Material.NETHER_STAR && rs14 == Material.NETHER_STAR && rs23 == Material.NETHER_STAR) {
                    game = false
                    onRedWin()
                    Bukkit.broadcastMessage("REDチームのかち！！！")
                }
            }
        } else if (InvName == "BLUE TEAM SHEET") {
            event.isCancelled = true
            if (slotnum == 16) {
                if (bs3 == Material.NETHER_STAR && bs4 == Material.NETHER_STAR && bs5 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                } else if (bs12 == Material.NETHER_STAR && bs13 == Material.NETHER_STAR && bs14 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                } else if (bs21 == Material.NETHER_STAR && bs22 == Material.NETHER_STAR && bs23 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                } else if (bs3 == Material.NETHER_STAR && bs13 == Material.NETHER_STAR && bs23 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                } else if (bs5 == Material.NETHER_STAR && bs13 == Material.NETHER_STAR && bs21 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                } else if (bs3 == Material.NETHER_STAR && bs12 == Material.NETHER_STAR && bs21 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                } else if (bs4 == Material.NETHER_STAR && bs13 == Material.NETHER_STAR && bs22 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                } else if (bs5 == Material.NETHER_STAR && bs14 == Material.NETHER_STAR && bs23 == Material.NETHER_STAR) {
                    game = false
                    onBlueWin()
                    Bukkit.broadcastMessage("BLUEチームのかち！！！")
                }
            }
        }
    }
    @EventHandler
    fun onCraft(event: CraftItemEvent){
        var craftItem = event.currentItem?.type
        var p = event.whoClicked.name
        if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("red")?.hasEntry(p) == true){
            val rtis = mutableListOf<Material>()
            var rti = RedInv
            rs3 = rti.getItem(3)?.type
            rs4 = rti.getItem(4)?.type
            rs5 = rti.getItem(5)?.type
            rs12 = rti.getItem(12)?.type
            rs13 = rti.getItem(13)?.type
            rs14 = rti.getItem(14)?.type
            rs21 = rti.getItem(21)?.type
            rs22 = rti.getItem(22)?.type
            rs23 = rti.getItem(23)?.type
            rtis.add(rs3!!)
            rtis.add(rs4!!)
            rtis.add(rs5!!)
            rtis.add(rs12!!)
            rtis.add(rs13!!)
            rtis.add(rs14!!)
            rtis.add(rs21!!)
            rtis.add(rs22!!)
            rtis.add(rs23!!)
            val rtsi = rtis.indexOf(craftItem)
            if(rtsi != -1){
                val rclear: Int
                when(rtsi) {
                    0 -> {
                        rclear = 3
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    1 -> {
                        rclear = 4
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    2 -> {
                        rclear = 5
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    3 -> {
                        rclear = 12
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    4 -> {
                        rclear = 13
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    5 -> {
                        rclear = 14
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    6 -> {
                        rclear = 21
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    7 -> {
                        rclear = 22
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                    8 -> {
                        rclear = 23
                        RedInv.setItem(rclear, ItemStack(Material.NETHER_STAR))
                    }
                }
            }

        } else if (Bukkit.getScoreboardManager()?.mainScoreboard?.getTeam("blue")?.hasEntry(p) == true) {
            val btis: MutableList<Material> = ArrayList()
            var bti = BlueInv
            bs3 = bti.getItem(3)?.type
            bs4 = bti.getItem(4)?.type
            bs5 = bti.getItem(5)?.type
            bs12 = bti.getItem(12)?.type
            bs13 = bti.getItem(13)?.type
            bs14 = bti.getItem(14)?.type
            bs21 = bti.getItem(21)?.type
            bs22 = bti.getItem(22)?.type
            bs23 = bti.getItem(23)?.type
            btis.add(bs3!!)
            btis.add(bs4!!)
            btis.add(bs5!!)
            btis.add(bs12!!)
            btis.add(bs13!!)
            btis.add(bs14!!)
            btis.add(bs21!!)
            btis.add(bs22!!)
            btis.add(bs23!!)
            var btsi = btis.indexOf(craftItem)
            if(btsi != -1){
                val bclear: Int
                when (btsi) {
                    0 -> {
                        //Bukkit.broadcastMessage("左上");
                        bclear = 3
                        //Bukkit.broadcastMessage(this.BlueInv.getItem(bclear) + "が入っています");
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    1 -> {
                        bclear = 4
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    2 -> {
                        bclear = 5
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    3 -> {
                        bclear = 12
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    4 -> {
                        bclear = 13
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    5 -> {
                        bclear = 14
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    6 -> {
                        bclear = 21
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    7 -> {
                        bclear = 22
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                    8 -> {
                        bclear = 23
                        BlueInv.setItem(bclear, ItemStack(Material.NETHER_STAR))
                    }
                }
            }
        }
    }
    @EventHandler
    fun onBreakblock(event: BlockBreakEvent) {
        if (!game) {
            event.isCancelled = true
        }
    }
    companion object {
        var rcount = 1
        var bcount = 1
    }
    override fun onDisable() {
        // Plugin shutdown logic
    }
}
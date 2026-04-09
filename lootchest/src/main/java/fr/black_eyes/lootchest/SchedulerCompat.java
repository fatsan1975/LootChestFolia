package fr.black_eyes.lootchest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * Scheduler bridge that keeps compatibility with classic Bukkit/Paper and Folia runtimes.
 */
public final class SchedulerCompat {

    private static final boolean FOLIA = detectFolia();

    private SchedulerCompat() {
    }

    public static boolean isFolia() {
        return FOLIA;
    }

    public static TaskHandle runGlobalRepeating(Plugin plugin, long delayTicks, long periodTicks, Runnable runnable) {
        if (!FOLIA) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            };
            task.runTaskTimer(plugin, delayTicks, periodTicks);
            return new TaskHandle(task, false);
        }
        Object task = invokeScheduler(getGlobalRegionScheduler(), "runAtFixedRate", new Class[]{Plugin.class, Consumer.class, long.class, long.class},
                plugin, (Consumer<Object>) ignored -> runnable.run(), delayTicks, periodTicks);
        return new TaskHandle(task, true);
    }

    public static TaskHandle runRegionRepeating(Plugin plugin, Location location, long delayTicks, long periodTicks, Runnable runnable) {
        if (!FOLIA) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            };
            task.runTaskTimer(plugin, delayTicks, periodTicks);
            return new TaskHandle(task, false);
        }
        Object task = invokeScheduler(getRegionScheduler(), "runAtFixedRate", new Class[]{Plugin.class, Location.class, Consumer.class, long.class, long.class},
                plugin, location, (Consumer<Object>) ignored -> runnable.run(), delayTicks, periodTicks);
        return new TaskHandle(task, true);
    }

    public static TaskHandle runRegionLater(Plugin plugin, Location location, long delayTicks, Runnable runnable) {
        if (!FOLIA) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            };
            task.runTaskLater(plugin, delayTicks);
            return new TaskHandle(task, false);
        }
        Object task = invokeScheduler(getRegionScheduler(), "runDelayed", new Class[]{Plugin.class, Location.class, Consumer.class, long.class},
                plugin, location, (Consumer<Object>) ignored -> runnable.run(), delayTicks);
        return new TaskHandle(task, true);
    }

    public static void runRegionNow(Plugin plugin, Location location, Runnable runnable) {
        if (!FOLIA) {
            Bukkit.getScheduler().runTask(plugin, runnable);
            return;
        }
        invokeScheduler(getRegionScheduler(), "run", new Class[]{Plugin.class, Location.class, Consumer.class},
                plugin, location, (Consumer<Object>) ignored -> runnable.run());
    }

    public static TaskHandle runGlobalLater(Plugin plugin, long delayTicks, Runnable runnable) {
        if (!FOLIA) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            };
            task.runTaskLater(plugin, delayTicks);
            return new TaskHandle(task, false);
        }
        Object task = invokeScheduler(getGlobalRegionScheduler(), "runDelayed", new Class[]{Plugin.class, Consumer.class, long.class},
                plugin, (Consumer<Object>) ignored -> runnable.run(), delayTicks);
        return new TaskHandle(task, true);
    }

    private static Object getRegionScheduler() {
        try {
            Method method = Bukkit.getServer().getClass().getMethod("getRegionScheduler");
            return method.invoke(Bukkit.getServer());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot access region scheduler", e);
        }
    }

    private static Object getGlobalRegionScheduler() {
        try {
            Method method = Bukkit.getServer().getClass().getMethod("getGlobalRegionScheduler");
            return method.invoke(Bukkit.getServer());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot access global region scheduler", e);
        }
    }

    private static Object invokeScheduler(Object scheduler, String methodName, Class<?>[] types, Object... args) {
        try {
            Method method = scheduler.getClass().getMethod(methodName, types);
            return method.invoke(scheduler, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot call scheduler method " + methodName, e);
        }
    }

    private static boolean detectFolia() {
        try {
            Bukkit.getServer().getClass().getMethod("getRegionScheduler");
            Bukkit.getServer().getClass().getMethod("getGlobalRegionScheduler");
            return true;
        } catch (NoSuchMethodException ignored) {
            return false;
        }
    }

    public static final class TaskHandle {
        private final Object task;
        private final boolean foliaTask;

        private TaskHandle(Object task, boolean foliaTask) {
            this.task = task;
            this.foliaTask = foliaTask;
        }

        public void cancel() {
            if (task == null) {
                return;
            }
            if (!foliaTask) {
                ((BukkitRunnable) task).cancel();
                return;
            }
            try {
                Method cancel = task.getClass().getMethod("cancel");
                cancel.invoke(task);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Cannot cancel Folia task", e);
            }
        }

        public boolean isCancelled() {
            if (task == null) {
                return true;
            }
            if (!foliaTask) {
                return ((BukkitRunnable) task).isCancelled();
            }
            try {
                Method cancelled = task.getClass().getMethod("isCancelled");
                return (boolean) cancelled.invoke(task);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                return false;
            }
        }
    }
}

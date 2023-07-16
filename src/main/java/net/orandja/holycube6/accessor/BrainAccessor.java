package net.orandja.holycube6.accessor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;
import java.util.Set;

@Mixin(Brain.class)
public interface BrainAccessor<E extends LivingEntity>  {

    @Accessor
    Map<Integer, Map<Activity, Set<Task<? super E>>>>  getTasks();

    @Invoker("tickMemories")
    void _tickMemories();

    @Invoker("tickSensors")
    void _tickSensors(ServerWorld world, E entity);

    @Invoker("startTasks")
    void _startTasks(ServerWorld world, E entity);

    @Invoker("updateTasks")
    void _updateTasks(ServerWorld world, E entity);

}

package com.minecrafttas.savestatemod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Unit;
import net.minecraft.world.level.ChunkPos;

public class WorldHacks {
	
	public static void unloadPlayers() {
		MinecraftServer server=Minecraft.getInstance().getSingleplayerServer();
		ServerLevel world=server.overworld();
		server.getPlayerList().getPlayers().forEach(player -> {
			world.getChunkSource().removeEntity(player);
		});
	}
	
	public static void unloadWorld() {
		MinecraftServer mcserver = Minecraft.getInstance().getSingleplayerServer();
		ServerLevel level=mcserver.overworld();
		ServerChunkCache chunkSource=level.getChunkSource();
		
		ChunkPos chunkPos = new ChunkPos(new BlockPos(level.getLevelData().getXSpawn(), 0, level.getLevelData().getZSpawn()));
		
		chunkSource.removeRegionTicket(TicketType.START, chunkPos, 11, Unit.INSTANCE);
		
//		ChunkMapDuck cmduck=(ChunkMapDuck) chunkSource.chunkMap;
//		cmduck.unloadChunks();
	}
	
	public static void loadWorld() {
		MinecraftServer mcserver = Minecraft.getInstance().getSingleplayerServer();
		ServerLevel level=mcserver.overworld();
		ServerChunkCache chunkSource=level.getChunkSource();
		
		ChunkPos chunkPos = new ChunkPos(new BlockPos(level.getLevelData().getXSpawn(), 0, level.getLevelData().getZSpawn()));
		chunkSource.addRegionTicket(TicketType.START, chunkPos, 11, Unit.INSTANCE);
	}
	
	public static void loadPlayer() {
		Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayers().forEach(player -> {
//			((AccessorEntity)player).invokeUnsetRemoved();
			MinecraftServer server =Minecraft.getInstance().getSingleplayerServer();
			ServerLevel serverLevel=server.overworld();
			serverLevel.getChunkSource().addEntity(player);
//			player.connection.send(new CustomRespawnPacket(serverLevel.dimensionType(), serverLevel.dimension(), BiomeManager.obfuscateSeed(serverLevel.getSeed()), player.gameMode.getGameModeForPlayer(), player.gameMode.getPreviousGameModeForPlayer(), serverLevel.isDebug(), serverLevel.isFlat(), true, true));
//			player.changeDimension(serverLevel);
		});
	}

}
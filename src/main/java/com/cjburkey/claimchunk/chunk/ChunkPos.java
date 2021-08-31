package com.cjburkey.claimchunk.chunk;

import org.bukkit.Chunk;

import java.util.Objects;

public final class ChunkPos {

    private final String world;
    private final int x;
    private final int z;

    /**
     * Create an instance of a chunk position from raw data.
     *
     * @param world The name of the world that this chunk is in.
     * @param x The x-coordinate of this chunk (in chunk coordinates).
     * @param z The y-coordinate of this chunk (in chunk coordinates).
     */
    public ChunkPos(String world, int x, int z) {
        this.world = world;
        this.x = x;
        this.z = z;
    }

    /**
     * Create an instance of a chunk position from Spigot's chunk position representation.
     *
     * @param chunk The Spigot chunk representation.
     */
    public ChunkPos(Chunk chunk) {
        this(chunk.getWorld().getName(), chunk.getX(), chunk.getZ());
    }

    /**
     * Get the name of the world that this chunk is in.
     *
     * @return The world name of this chunk.
     */
    public String getWorld() {
        return world;
    }

    /**
     * Get the x-coordinate of this chunk.
     *
     * @return The x-coordinate of this chunk (in chunk coordinates).
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate of this chunk.
     *
     * @return The y-coordinate of this chunk (in chunk coordinates).
     */
    public int getZ() {
        return z;
    }

    /**
     * Helper method to get a chunk north, relative to this one.
     *
     * @since 0.0.23
     */
    public ChunkPos north() {
        return new ChunkPos(world, x, z - 1);
    }

    /**
     * Helper method to get a chunk south, relative to this one.
     *
     * @since 0.0.23
     */
    public ChunkPos south() {
        return new ChunkPos(world, x, z + 1);
    }

    /**
     * Helper method to get a chunk east, relative to this one.
     *
     * @since 0.0.23
     */
    public ChunkPos east() {
        return new ChunkPos(world, x - 1, z);
    }

    /**
     * Helper method to get a chunk west, relative to this one.
     *
     * @since 0.0.23
     */
    public ChunkPos west() {
        return new ChunkPos(world, x + 1, z);
    }

    @Override
    public String toString() {
        return String.format("%s, %s in %s", x, z, world);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChunkPos chunkPos = (ChunkPos) o;
        return x == chunkPos.x && z == chunkPos.z && Objects.equals(world, chunkPos.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, z);
    }
}

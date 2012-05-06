// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, MapGenCaves11201, NoiseGeneratorOctaves11201, Block, 
//            BlockGrass, Chunk, MapGenBase11201, BlockSand, 
//            World, WorldGenDungeons, WorldGenClay1102, WorldGenMinable, 
//            WorldInfo, WorldGenTrees, WorldGenBigTree, WorldGenerator, 
//            WorldGenFlowers, BlockFlower, WorldGenReed, WorldGenCactus, 
//            WorldGenLiquids, Material, WorldChunkManager, ChunkCoordIntPair, 
//            BiomeGenBase, EnumCreatureType, ChunkPosition, IProgressUpdate

public class ChunkProvider11201
    implements IChunkProvider
{

    private Random rand;
    private NoiseGeneratorOctaves11201 noiseGen1;
    private NoiseGeneratorOctaves11201 noiseGen2;
    private NoiseGeneratorOctaves11201 noiseGen3;
    private NoiseGeneratorOctaves11201 noiseGen4;
    private NoiseGeneratorOctaves11201 noiseGen5;
    public NoiseGeneratorOctaves11201 noiseGen6;
    public NoiseGeneratorOctaves11201 noiseGen7;
    public NoiseGeneratorOctaves11201 noiseGen8;
    private World worldObj;
    private double noise4[];
    private double noise1[];
    private double noise2[];
    private double noise3[];
    private MapGenBase11201 caveGenerator;
    double field_919_d[];
    double field_918_e[];
    double field_917_f[];
    double field_916_g[];
    double field_915_h[];
    int unusedIntArray32x32[][];
    private boolean snowWorld;

    public ChunkProvider11201(World world, long l)
    {
        snowWorld = false;
        noise1 = new double[256];
        noise2 = new double[256];
        noise3 = new double[256];
        caveGenerator = new MapGenCaves11201();
        unusedIntArray32x32 = new int[32][32];
        worldObj = world;
        rand = new Random(l);
        noiseGen1 = new NoiseGeneratorOctaves11201(rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves11201(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves11201(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves11201(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves11201(rand, 4);
        noiseGen6 = new NoiseGeneratorOctaves11201(rand, 10);
        noiseGen7 = new NoiseGeneratorOctaves11201(rand, 16);
        noiseGen8 = new NoiseGeneratorOctaves11201(rand, 8);
    }

    public void generateTerrain(int i, int j, byte abyte0[])
    {
        byte byte0 = 4;
        byte byte1 = 64;
        int k = byte0 + 1;
        byte byte2 = 17;
        int l = byte0 + 1;
        noise4 = initializeNoiseField(noise4, i * byte0, 0, j * byte0, k, byte2, l);
        for(int i1 = 0; i1 < byte0; i1++)
        {
            for(int j1 = 0; j1 < byte0; j1++)
            {
                for(int k1 = 0; k1 < 16; k1++)
                {
                    double d = 0.125D;
                    double d1 = noise4[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d2 = noise4[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d3 = noise4[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d4 = noise4[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d5 = (noise4[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d1) * d;
                    double d6 = (noise4[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d2) * d;
                    double d7 = (noise4[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d3) * d;
                    double d8 = (noise4[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d4) * d;
                    for(int l1 = 0; l1 < 8; l1++)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for(int i2 = 0; i2 < 4; i2++)
                        {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            char c = '\200';
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for(int k2 = 0; k2 < 4; k2++)
                            {
                                int l2 = 0;
                                if(k1 * 8 + l1 < byte1)
                                {
                                    if(snowWorld && k1 * 8 + l1 >= byte1 - 1)
                                    {
                                        l2 = Block.ice.blockID;
                                    } else
                                    {
                                        l2 = Block.waterStill.blockID;
                                    }
                                }
                                if(d15 > 0.0D)
                                {
                                    l2 = Block.stone.blockID;
                                }
                                abyte0[j2] = (byte)l2;
                                j2 += c;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }

                }

            }

        }

    }

    public void replaceBlocksForBiome(int i, int j, byte abyte0[])
    {
        byte byte0 = 64;
        double d = 0.03125D;
        noise1 = noiseGen4.generateNoiseOctaves(noise1, i * 16, j * 16, 0.0D, 16, 16, 1, d, d, 1.0D);
        noise2 = noiseGen4.generateNoiseOctaves(noise2, j * 16, 109.0134D, i * 16, 16, 1, 16, d, 1.0D, d);
        noise3 = noiseGen5.generateNoiseOctaves(noise3, i * 16, j * 16, 0.0D, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for(int k = 0; k < 16; k++)
        {
            for(int l = 0; l < 16; l++)
            {
                boolean flag = noise1[k + l * 16] + rand.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = noise2[k + l * 16] + rand.nextDouble() * 0.20000000000000001D > 3D;
                int i1 = (int)(noise3[k + l * 16] / 3D + 3D + rand.nextDouble() * 0.25D);
                int j1 = -1;
                byte byte1 = (byte)Block.grass.blockID;
                byte byte2 = (byte)Block.dirt.blockID;
                for(int k1 = 127; k1 >= 0; k1--)
                {
                    int l1 = (k * 16 + l) * 128 + k1;
                    if(k1 <= (0 + rand.nextInt(6)) - 1)
                    {
                        abyte0[l1] = (byte)Block.bedrock.blockID;
                        continue;
                    }
                    byte byte3 = abyte0[l1];
                    if(byte3 == 0)
                    {
                        j1 = -1;
                        continue;
                    }
                    if(byte3 != Block.stone.blockID)
                    {
                        continue;
                    }
                    if(j1 == -1)
                    {
                        if(i1 <= 0)
                        {
                            byte1 = 0;
                            byte2 = (byte)Block.stone.blockID;
                        } else
                        if(k1 >= byte0 - 4 && k1 <= byte0 + 1)
                        {
                            byte1 = (byte)Block.grass.blockID;
                            byte2 = (byte)Block.dirt.blockID;
                            if(flag1)
                            {
                                byte1 = 0;
                            }
                            if(flag1)
                            {
                                byte2 = (byte)Block.stone.blockID;
                            }
                            if(flag)
                            {
                                byte1 = (byte)Block.sand.blockID;
                            }
                            if(flag)
                            {
                                byte2 = (byte)Block.sand.blockID;
                            }
                        }
                        if(k1 < byte0 && byte1 == 0)
                        {
                            byte1 = (byte)Block.waterStill.blockID;
                        }
                        j1 = i1;
                        if(k1 >= byte0 - 1)
                        {
                            abyte0[l1] = byte1;
                        } else
                        {
                            abyte0[l1] = byte2;
                        }
                        continue;
                    }
                    if(j1 > 0)
                    {
                        j1--;
                        abyte0[l1] = byte2;
                    }
                }

            }

        }

    }

    public Chunk provideChunk(int i, int j)
    {
        rand.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        byte abyte0[] = new byte[32768];
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        generateTerrain(i, j, abyte0);
        replaceBlocksForBiome(i, j, abyte0);
        caveGenerator.generate(this, worldObj, i, j, abyte0);
        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] initializeNoiseField(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if(ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        field_916_g = noiseGen6.generateNoiseOctaves(field_916_g, i, j, k, l, 1, j1, 1.0D, 0.0D, 1.0D);
        field_915_h = noiseGen7.generateNoiseOctaves(field_915_h, i, j, k, l, 1, j1, 100D, 0.0D, 100D);
        field_919_d = noiseGen3.generateNoiseOctaves(field_919_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_918_e = noiseGen1.generateNoiseOctaves(field_918_e, i, j, k, l, i1, j1, d, d1, d);
        field_917_f = noiseGen2.generateNoiseOctaves(field_917_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        for(int i2 = 0; i2 < l; i2++)
        {
            for(int j2 = 0; j2 < j1; j2++)
            {
                double d2 = (field_916_g[l1] + 256D) / 512D;
                if(d2 > 1.0D)
                {
                    d2 = 1.0D;
                }
                double d3 = 0.0D;
                double d4 = field_915_h[l1] / 8000D;
                if(d4 < 0.0D)
                {
                    d4 = -d4;
                }
                d4 = d4 * 3D - 3D;
                if(d4 < 0.0D)
                {
                    d4 /= 2D;
                    if(d4 < -1D)
                    {
                        d4 = -1D;
                    }
                    d4 /= 1.3999999999999999D;
                    d4 /= 2D;
                    d2 = 0.0D;
                } else
                {
                    if(d4 > 1.0D)
                    {
                        d4 = 1.0D;
                    }
                    d4 /= 6D;
                }
                d2 += 0.5D;
                d4 = (d4 * (double)i1) / 16D;
                double d5 = (double)i1 / 2D + d4 * 4D;
                l1++;
                for(int k2 = 0; k2 < i1; k2++)
                {
                    double d6 = 0.0D;
                    double d7 = (((double)k2 - d5) * 12D) / d2;
                    if(d7 < 0.0D)
                    {
                        d7 *= 4D;
                    }
                    double d8 = field_918_e[k1] / 512D;
                    double d9 = field_917_f[k1] / 512D;
                    double d10 = (field_919_d[k1] / 10D + 1.0D) / 2D;
                    if(d10 < 0.0D)
                    {
                        d6 = d8;
                    } else
                    if(d10 > 1.0D)
                    {
                        d6 = d9;
                    } else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }
                    d6 -= d7;
                    if(k2 > i1 - 4)
                    {
                        double d11 = (float)(k2 - (i1 - 4)) / 3F;
                        d6 = d6 * (1.0D - d11) + -10D * d11;
                    }
                    if((double)k2 < d3)
                    {
                        double d12 = (d3 - (double)k2) / 4D;
                        if(d12 < 0.0D)
                        {
                            d12 = 0.0D;
                        }
                        if(d12 > 1.0D)
                        {
                            d12 = 1.0D;
                        }
                        d6 = d6 * (1.0D - d12) + -10D * d12;
                    }
                    ad[k1] = d6;
                    k1++;
                }

            }

        }

        return ad;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockSand.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
        rand.setSeed(worldObj.getWorldSeed());
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        long l2 = (rand.nextLong() / 2L) * 2L + 1L;
        rand.setSeed((long)i * l1 + (long)j * l2 ^ worldObj.getWorldSeed());
        double d = 0.25D;
        for(int i1 = 0; i1 < 8; i1++)
        {
            int i4 = k + rand.nextInt(16) + 8;
            int j6 = rand.nextInt(128);
            int j11 = l + rand.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(worldObj, rand, i4, j6, j11);
        }

        for(int j1 = 0; j1 < 9; j1++)
        {
            int j4 = k + rand.nextInt(16);
            int k6 = rand.nextInt(128);
            int k11 = l + rand.nextInt(16);
            (new WorldGenClay1102(32)).generate(worldObj, rand, j4, k6, k11);
        }

        for(int k1 = 0; k1 < 20; k1++)
        {
            int k4 = k + rand.nextInt(16);
            int l6 = rand.nextInt(128);
            int l11 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.dirt.blockID, 32)).generate(worldObj, rand, k4, l6, l11);
        }

        for(int i2 = 0; i2 < 20; i2++)
        {
            int l4 = k + rand.nextInt(16);
            int i7 = rand.nextInt(128);
            int i12 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreCoal.blockID, 16)).generate(worldObj, rand, l4, i7, i12);
        }

        for(int j2 = 0; j2 < 20; j2++)
        {
            int i5 = k + rand.nextInt(16);
            int j7 = rand.nextInt(64);
            int j12 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreIron.blockID, 8)).generate(worldObj, rand, i5, j7, j12);
        }

        for(int k2 = 0; k2 < 2; k2++)
        {
            int j5 = k + rand.nextInt(16);
            int k7 = rand.nextInt(32);
            int k12 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreGold.blockID, 8)).generate(worldObj, rand, j5, k7, k12);
        }

        for(int i3 = 0; i3 < 8; i3++)
        {
            int k5 = k + rand.nextInt(16);
            int l7 = rand.nextInt(16);
            int l12 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreRedstone.blockID, 7)).generate(worldObj, rand, k5, l7, l12);
        }

        for(int j3 = 0; j3 < 1; j3++)
        {
            int l5 = k + rand.nextInt(16);
            int i8 = rand.nextInt(16);
            int i13 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreDiamond.blockID, 7)).generate(worldObj, rand, l5, i8, i13);
        }

        if(worldObj.getWorldInfo().getGeneratorType() == 1)
        {
            for(int k3 = 0; k3 < 1; k3++)
            {
                int i6 = k + rand.nextInt(16);
                int j8 = rand.nextInt(16) + rand.nextInt(16);
                int j13 = l + rand.nextInt(16);
                (new WorldGenMinable(Block.oreLapis.blockID, 6)).generate(worldObj, rand, i6, j8, j13);
            }

        }
        d = 0.5D;
        int l3 = (int)((noiseGen8.func_806_a((double)k * d, (double)l * d) / 8D + rand.nextDouble() * 4D + 4D) / 3D);
        if(l3 < 0)
        {
            l3 = 0;
        }
        if(rand.nextInt(10) == 0)
        {
            l3++;
        }
        Object obj = new WorldGenTrees(false);
        if(rand.nextInt(10) == 0)
        {
            obj = new WorldGenBigTree(false);
        }
        for(int k8 = 0; k8 < l3; k8++)
        {
            int k13 = k + rand.nextInt(16) + 8;
            int j16 = l + rand.nextInt(16) + 8;
            ((WorldGenerator)obj).func_517_a(1.0D, 1.0D, 1.0D);
            ((WorldGenerator)obj).generate(worldObj, rand, k13, worldObj.getHeightValue(k13, j16), j16);
        }

        for(int l8 = 0; l8 < 2; l8++)
        {
            int l13 = k + rand.nextInt(16) + 8;
            int k16 = rand.nextInt(128);
            int i19 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantYellow.blockID)).generate(worldObj, rand, l13, k16, i19);
        }

        if(rand.nextInt(2) == 0)
        {
            int i9 = k + rand.nextInt(16) + 8;
            int i14 = rand.nextInt(128);
            int l16 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantRed.blockID)).generate(worldObj, rand, i9, i14, l16);
        }
        if(rand.nextInt(4) == 0)
        {
            int j9 = k + rand.nextInt(16) + 8;
            int j14 = rand.nextInt(128);
            int i17 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, j9, j14, i17);
        }
        if(rand.nextInt(8) == 0)
        {
            int k9 = k + rand.nextInt(16) + 8;
            int k14 = rand.nextInt(128);
            int j17 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, k9, k14, j17);
        }
        for(int l9 = 0; l9 < 10; l9++)
        {
            int l14 = k + rand.nextInt(16) + 8;
            int k17 = rand.nextInt(128);
            int j19 = l + rand.nextInt(16) + 8;
            (new WorldGenReed()).generate(worldObj, rand, l14, k17, j19);
        }

        for(int i10 = 0; i10 < 1; i10++)
        {
            int i15 = k + rand.nextInt(16) + 8;
            int l17 = rand.nextInt(128);
            int k19 = l + rand.nextInt(16) + 8;
            (new WorldGenCactus()).generate(worldObj, rand, i15, l17, k19);
        }

        for(int j10 = 0; j10 < 10; j10++)
        {
            int j15 = k + rand.nextInt(16);
            int i18 = rand.nextInt(128);
            int l19 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.gravel.blockID, 32)).generate(worldObj, rand, j15, i18, l19);
        }

        for(int k10 = 0; k10 < 50; k10++)
        {
            int k15 = k + rand.nextInt(16) + 8;
            int j18 = rand.nextInt(rand.nextInt(120) + 8);
            int i20 = l + rand.nextInt(16) + 8;
            (new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj, rand, k15, j18, i20);
        }

        for(int l10 = 0; l10 < 20; l10++)
        {
            int l15 = k + rand.nextInt(16) + 8;
            int k18 = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
            int j20 = l + rand.nextInt(16) + 8;
            (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(worldObj, rand, l15, k18, j20);
        }

        for(int i11 = k + 8 + 0; i11 < k + 8 + 16; i11++)
        {
            for(int i16 = l + 8 + 0; i16 < l + 8 + 16; i16++)
            {
                int l18 = worldObj.findTopSolidBlockLegacy(i11, i16);
                if(snowWorld && l18 > 0 && l18 < 128 && worldObj.getBlockId(i11, l18, i16) == 0 && worldObj.getBlockMaterial(i11, l18 - 1, i16).getIsSolid() && worldObj.getBlockMaterial(i11, l18 - 1, i16) != Material.ice)
                {
                    worldObj.setBlockWithNotify(i11, l18, i16, Block.snow.blockID);
                }
            }

        }

        BlockSand.fallInstantly = false;
    }

    public List func_40377_a(EnumCreatureType enumcreaturetype, int i, int j, int k)
    {
        WorldChunkManager worldchunkmanager = worldObj.getWorldChunkManager();
        if(worldchunkmanager == null)
        {
            return null;
        }
        BiomeGenBase biomegenbase = worldchunkmanager.getBiomeGenAtChunkCoord(new ChunkCoordIntPair(i >> 4, k >> 4));
        if(biomegenbase == null)
        {
            return null;
        } else
        {
            return biomegenbase.getSpawnableList(enumcreaturetype);
        }
    }

    public ChunkPosition func_40376_a(World world, String s, int i, int j, int k)
    {
        return null;
    }

    public Chunk loadChunk(int i, int j)
    {
        return provideChunk(i, j);
    }

    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }
}

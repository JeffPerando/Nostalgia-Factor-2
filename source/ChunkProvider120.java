// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, MapGenCaves120, NoiseGeneratorOctaves120, Block, 
//            BiomeGenBase, Chunk, World, WorldChunkManager, 
//            BlockSand, WorldGenLakes, WorldGenDungeons, WorldGenClay1102, 
//            WorldGenMinable, WorldGenTrees, WorldGenBigTree, WorldGenerator, 
//            WorldGenFlowers, BlockFlower, WorldGenReed, WorldGenPumpkin, 
//            WorldGenCactus1102, WorldGenLiquids, Material, ChunkCoordIntPair, 
//            EnumCreatureType, ChunkPosition, IProgressUpdate

public class ChunkProvider120 implements IChunkProvider
{
    private Random rand;
    private NoiseGeneratorOctaves120 noiseGen1;
    private NoiseGeneratorOctaves120 noiseGen2;
    private NoiseGeneratorOctaves120 noiseGen3;
    private NoiseGeneratorOctaves120 noiseGen4;
    public NoiseGeneratorOctaves120 noiseGen5;
    public NoiseGeneratorOctaves120 noiseGen7;
    public NoiseGeneratorOctaves120 noiseGen8;
    private World worldObj;
    private double noise4[];
    private double noise1[];
    private double noise2[];
    private double noise3[];
    private MapGenCaves120 caveGenerator;
    private BiomeGenBase biomesForGeneration[];
    double noise5[];
    double noise8[];
    double noise7[];
    double noise6[];
    int unusedint32x32[][];
    private double field_4178_w[];

    public ChunkProvider120(World world, long l)
    {
        noise1 = new double[256];
        noise2 = new double[256];
        noise3 = new double[256];
        caveGenerator = new MapGenCaves120();
        unusedint32x32 = new int[32][32];
        worldObj = world;
        rand = new Random(l);
        noiseGen1 = new NoiseGeneratorOctaves120(rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves120(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves120(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves120(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves120(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves120(rand, 10);
        noiseGen7 = new NoiseGeneratorOctaves120(rand, 16);
        noiseGen8 = new NoiseGeneratorOctaves120(rand, 8);
    }

    public void generateTerrain(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[], double ad[])
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
                                double d17 = ad[(i1 * 4 + i2) * 16 + (j1 * 4 + k2)];
                                int l2 = 0;
                                if(k1 * 8 + l1 < byte1)
                                {
                                    if(d17 < 0.5D && k1 * 8 + l1 >= byte1 - 1)
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

    public void replaceBlocksForBiome(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[])
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
                BiomeGenBase biomegenbase = abiomegenbase[k * 16 + l];
                boolean flag = noise1[k + l * 16] + rand.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = noise2[k + l * 16] + rand.nextDouble() * 0.20000000000000001D > 3D;
                int i1 = (int)(noise3[k + l * 16] / 3D + 3D + rand.nextDouble() * 0.25D);
                int j1 = -1;
                byte byte1 = biomegenbase.topBlock;
                byte byte2 = biomegenbase.fillerBlock;
                for(int k1 = 127; k1 >= 0; k1--)
                {
                    int l1 = (k * 16 + l) * 128 + k1;
                    if(k1 <= 0 + rand.nextInt(5))
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
                            byte1 = biomegenbase.topBlock;
                            byte2 = biomegenbase.fillerBlock;
                            if(flag1)
                            {
                                byte1 = 0;
                            }
                            if(flag1)
                            {
                                byte2 = (byte)Block.gravel.blockID;
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
        biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, i * 16, j * 16, 16, 16);
        generateTerrain(i, j, abyte0, biomesForGeneration, field_4178_w);
        replaceBlocksForBiome(i, j, abyte0, biomesForGeneration);
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
        noise5 = noiseGen5.func_4109_a(noise5, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        noise6 = noiseGen7.func_4109_a(noise6, i, k, l, j1, 200D, 200D, 0.5D);
        noise3 = noiseGen3.generateNoiseOctaves(noise3, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        noise1 = noiseGen1.generateNoiseOctaves(noise1, i, j, k, l, i1, j1, d, d1, d);
        noise2 = noiseGen2.generateNoiseOctaves(noise2, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        int i2 = 16 / l;
        for(int j2 = 0; j2 < l; j2++)
        {
            int k2 = j2 * i2 + i2 / 2;
            for(int l2 = 0; l2 < j1; l2++)
            {
                int i3 = l2 * i2 + i2 / 2;
                double d4 = 1.0D - d1;
                d4 *= d4;
                d4 *= d4;
                d4 = 1.0D - d4;
                double d5 = (noise5[l1] + 256D) / 512D;
                d5 *= d4;
                if(d5 > 1.0D)
                {
                    d5 = 1.0D;
                }
                double d6 = noise6[l1] / 8000D;
                if(d6 < 0.0D)
                {
                    d6 = -d6 * 0.29999999999999999D;
                }
                d6 = d6 * 3D - 2D;
                if(d6 < 0.0D)
                {
                    d6 /= 2D;
                    if(d6 < -1D)
                    {
                        d6 = -1D;
                    }
                    d6 /= 1.3999999999999999D;
                    d6 /= 2D;
                    d5 = 0.0D;
                } else
                {
                    if(d6 > 1.0D)
                    {
                        d6 = 1.0D;
                    }
                    d6 /= 8D;
                }
                if(d5 < 0.0D)
                {
                    d5 = 0.0D;
                }
                d5 += 0.5D;
                d6 = (d6 * (double)i1) / 16D;
                double d7 = (double)i1 / 2D + d6 * 4D;
                l1++;
                for(int j3 = 0; j3 < i1; j3++)
                {
                    double d8 = 0.0D;
                    double d9 = (((double)j3 - d7) * 12D) / d5;
                    if(d9 < 0.0D)
                    {
                        d9 *= 4D;
                    }
                    double d10 = noise1[k1] / 512D;
                    double d11 = noise2[k1] / 512D;
                    double d12 = (noise3[k1] / 10D + 1.0D) / 2D;
                    if(d12 < 0.0D)
                    {
                        d8 = d10;
                    } else
                    if(d12 > 1.0D)
                    {
                        d8 = d11;
                    } else
                    {
                        d8 = d10 + (d11 - d10) * d12;
                    }
                    d8 -= d9;
                    if(j3 > i1 - 4)
                    {
                        double d13 = (float)(j3 - (i1 - 4)) / 3F;
                        d8 = d8 * (1.0D - d13) + -10D * d13;
                    }
                    ad[k1] = d8;
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
        BiomeGenBase biomegenbase = worldObj.getWorldChunkManager().getBiomeGenAt(k + 16, l + 16);
        rand.setSeed(worldObj.getSeed());
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        long l2 = (rand.nextLong() / 2L) * 2L + 1L;
        rand.setSeed((long)i * l1 + (long)j * l2 ^ worldObj.getSeed());
        double d = 0.25D;
        if(rand.nextInt(4) == 0)
        {
            int i1 = k + rand.nextInt(16) + 8;
            int j5 = rand.nextInt(128);
            int i9 = l + rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(worldObj, rand, i1, j5, i9);
        }
        if(rand.nextInt(8) == 0)
        {
            int j1 = k + rand.nextInt(16) + 8;
            int k5 = rand.nextInt(rand.nextInt(120) + 8);
            int j9 = l + rand.nextInt(16) + 8;
            if(k5 < 64 || rand.nextInt(10) == 0)
            {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(worldObj, rand, j1, k5, j9);
            }
        }
        for(int k1 = 0; k1 < 8; k1++)
        {
            int l5 = k + rand.nextInt(16) + 8;
            int k9 = rand.nextInt(128);
            int k12 = l + rand.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(worldObj, rand, l5, k9, k12);
        }

        for(int i2 = 0; i2 < 9; i2++)
        {
            int i6 = k + rand.nextInt(16);
            int l9 = rand.nextInt(128);
            int l12 = l + rand.nextInt(16);
            (new WorldGenClay1102(32)).generate(worldObj, rand, i6, l9, l12);
        }

        for(int j2 = 0; j2 < 20; j2++)
        {
            int j6 = k + rand.nextInt(16);
            int i10 = rand.nextInt(128);
            int i13 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.dirt.blockID, 32)).generate(worldObj, rand, j6, i10, i13);
        }

        for(int k2 = 0; k2 < 10; k2++)
        {
            int k6 = k + rand.nextInt(16);
            int j10 = rand.nextInt(128);
            int j13 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.gravel.blockID, 32)).generate(worldObj, rand, k6, j10, j13);
        }

        for(int i3 = 0; i3 < 20; i3++)
        {
            int l6 = k + rand.nextInt(16);
            int k10 = rand.nextInt(128);
            int k13 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreCoal.blockID, 16)).generate(worldObj, rand, l6, k10, k13);
        }

        for(int j3 = 0; j3 < 20; j3++)
        {
            int i7 = k + rand.nextInt(16);
            int l10 = rand.nextInt(64);
            int l13 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreIron.blockID, 8)).generate(worldObj, rand, i7, l10, l13);
        }

        for(int k3 = 0; k3 < 2; k3++)
        {
            int j7 = k + rand.nextInt(16);
            int i11 = rand.nextInt(32);
            int i14 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreGold.blockID, 8)).generate(worldObj, rand, j7, i11, i14);
        }

        for(int l3 = 0; l3 < 8; l3++)
        {
            int k7 = k + rand.nextInt(16);
            int j11 = rand.nextInt(16);
            int j14 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreRedstone.blockID, 7)).generate(worldObj, rand, k7, j11, j14);
        }

        for(int i4 = 0; i4 < 1; i4++)
        {
            int l7 = k + rand.nextInt(16);
            int k11 = rand.nextInt(16);
            int k14 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreDiamond.blockID, 7)).generate(worldObj, rand, l7, k11, k14);
        }

        for(int j4 = 0; j4 < 1; j4++)
        {
            int i8 = k + rand.nextInt(16);
            int l11 = rand.nextInt(16) + rand.nextInt(16);
            int l14 = l + rand.nextInt(16);
            (new WorldGenMinable(Block.oreLapis.blockID, 6)).generate(worldObj, rand, i8, l11, l14);
        }

        /**
         * And this is where obfuscated code sucks. -Hawk
         */
        
        
    	int placeholderInt;
        if(rand.nextInt(4) == 0)
        {
            int k4 = k + rand.nextInt(16) + 8;
            int j8 = rand.nextInt(placeholderInt);
            int i12 = l + rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(worldObj, rand, k4, j8, i12);
        }
        if(rand.nextInt(8) == 0)
        {
            int l4 = k + rand.nextInt(16) + 8;
            int k8 = rand.nextInt(rand.nextInt(placeholderInt - 8) + 8);
            int j12 = l + rand.nextInt(16) + 8;
            if(k8 < placeholderInt || rand.nextInt(10) == 0)
            {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(worldObj, rand, l4, k8, j12);
            }
        }
        d = 0.5D;
        int i5 = (int)((noiseGen8.func_806_a((double)k * d, (double)l * d) / 8D + rand.nextDouble() * 4D + 4D) / 3D);
        int l8 = 0;
        if(rand.nextInt(10) == 0)
        {
            l8++;
        }
        if(biomegenbase == BiomeGenBase.forest5)
        {
            l8 += i5 + 5;
        }
        if(biomegenbase == BiomeGenBase.rainforest5)
        {
            l8 += i5 + 5;
        }
        if(biomegenbase == BiomeGenBase.seasonalForest5)
        {
            l8 += i5 + 2;
        }
        if(biomegenbase == BiomeGenBase.taiga5)
        {
            l8 += i5 + 5;
        }
        if(biomegenbase == BiomeGenBase.dA)
        {
            l8 -= 20;
        }
        if(biomegenbase == BiomeGenBase.tundra5)
        {
            l8 -= 20;
        }
        if(biomegenbase == BiomeGenBase.plains5)
        {
            l8 -= 20;
        }
        Object obj = new WorldGenTrees(false);
        if(rand.nextInt(10) == 0)
        {
            obj = new WorldGenBigTree(false);
        }
        if(biomegenbase == BiomeGenBase.rainforest5 && rand.nextInt(3) == 0)
        {
            obj = new WorldGenBigTree(false);
        }
        for(int i15 = 0; i15 < l8; i15++)
        {
            int i17 = k + rand.nextInt(16) + 8;
            int l19 = l + rand.nextInt(16) + 8;
            ((WorldGenerator)obj).func_517_a(1.0D, 1.0D, 1.0D);
            ((WorldGenerator)obj).generate(worldObj, rand, i17, worldObj.getHeightValue(i17, l19), l19);
        }

        for(int j15 = 0; j15 < 2; j15++)
        {
            int j17 = k + rand.nextInt(16) + 8;
            int i20 = rand.nextInt(128);
            int k22 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantYellow.blockID)).generate(worldObj, rand, j17, i20, k22);
        }

        if(rand.nextInt(2) == 0)
        {
            int k15 = k + rand.nextInt(16) + 8;
            int k17 = rand.nextInt(128);
            int j20 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantRed.blockID)).generate(worldObj, rand, k15, k17, j20);
        }
        if(rand.nextInt(4) == 0)
        {
            int l15 = k + rand.nextInt(16) + 8;
            int l17 = rand.nextInt(128);
            int k20 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, l15, l17, k20);
        }
        if(rand.nextInt(8) == 0)
        {
            int i16 = k + rand.nextInt(16) + 8;
            int i18 = rand.nextInt(128);
            int l20 = l + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, i16, i18, l20);
        }
        for(int j16 = 0; j16 < 10; j16++)
        {
            int j18 = k + rand.nextInt(16) + 8;
            int i21 = rand.nextInt(128);
            int l22 = l + rand.nextInt(16) + 8;
            (new WorldGenReed()).generate(worldObj, rand, j18, i21, l22);
        }

        if(rand.nextInt(32) == 0)
        {
            int k16 = k + rand.nextInt(16) + 8;
            int k18 = rand.nextInt(128);
            int j21 = l + rand.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(worldObj, rand, k16, k18, j21);
        }
        int l16 = 0;
        if(biomegenbase == BiomeGenBase.desert)
        {
            l16 += 10;
        }
        for(int l18 = 0; l18 < l16; l18++)
        {
            int k21 = k + rand.nextInt(16) + 8;
            int i23 = rand.nextInt(128);
            int i24 = l + rand.nextInt(16) + 8;
            (new WorldGenCactus1102()).generate(worldObj, rand, k21, i23, i24);
        }

        for(int i19 = 0; i19 < 50; i19++)
        {
            int l21 = k + rand.nextInt(16) + 8;
            int j23 = rand.nextInt(rand.nextInt(120) + 8);
            int j24 = l + rand.nextInt(16) + 8;
            (new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj, rand, l21, j23, j24);
        }

        for(int j19 = 0; j19 < 20; j19++)
        {
            int i22 = k + rand.nextInt(16) + 8;
            int k23 = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
            int k24 = l + rand.nextInt(16) + 8;
            (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(worldObj, rand, i22, k23, k24);
        }

        field_4178_w = worldObj.getWorldChunkManager().getTemperatures(field_4178_w, k + 8, l + 8, 16, 16);
        for(int k19 = k + 8; k19 < k + 8 + 16; k19++)
        {
            for(int j22 = l + 8; j22 < l + 8 + 16; j22++)
            {
                int l23 = k19 - (k + 8);
                int l24 = j22 - (l + 8);
                int i25 = worldObj.findTopSolidBlockLegacy(k19, j22);
                double d1 = field_4178_w[l23 * 16 + l24] - ((double)(i25 - 64) / 64D) * 0.29999999999999999D;
                if(d1 < 0.5D && i25 > 0 && i25 < 128 && worldObj.getBlockId(k19, i25, j22) == 0 && worldObj.getBlockMaterial(k19, i25 - 1, j22).getIsSolid() && worldObj.getBlockMaterial(k19, i25 - 1, j22) != Material.ice)
                {
                    worldObj.setBlockWithNotify(k19, i25, j22, Block.snow.blockID);
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
        BiomeGenBase biomegenbase = worldchunkmanager.getBiomeGenAtChunkCoordLegacy(new ChunkCoordIntPair(i >> 4, k >> 4));
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

	@Override
	public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3,
			int var4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChunkPosition findClosestStructure(World var1, String var2,
			int var3, int var4, int var5) {
		// TODO Auto-generated method stub
		return null;
	}
}

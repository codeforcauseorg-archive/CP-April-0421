package org.codeforcause.lecture5;

public class EggDrop {
    public static void main(String[] args) {
        int floors = 10;
        int eggs = 2;
        Integer[][] mem = new Integer[floors+1][eggs+1];
//        System.out.println(eggDrop(floors, eggs));
//        System.out.println(eggDropDp(floors, eggs, mem));
        System.out.println(eggDropItr(floors, eggs, mem));
    }

    private static int eggDrop(int floors, int eggs) {
        if (floors < 2 || eggs == 1) {
            return floors;
        }
        int best = floors;
        for (int f = 1; f <= floors; f++) {
            int broken = eggDrop(f-1, eggs-1);
            int safe = eggDrop(floors-f, eggs);
            int floorWorst = 1 + Math.max(broken, safe);
            if (floorWorst < best) {
                best = floorWorst;
            }
        }
        return best;
    }

    private static int eggDropDp(int floors, int eggs, Integer[][] mem) {
        if (floors < 2 || eggs == 1) {
            return floors;
        }
        if (mem[floors][eggs] != null) {
            return mem[floors][eggs];
        } else {
            mem[floors][eggs] = floors;
            for (int f = 1; f <= floors; f++) {
                int broken = eggDropDp(f - 1, eggs - 1, mem);
                int safe = eggDropDp(floors - f, eggs, mem);
                int floorWorst = 1 + Math.max(broken, safe);
                if (floorWorst < mem[floors][eggs]) {
                    mem[floors][eggs] = floorWorst;
                }
            }
        }
        return mem[floors][eggs];
    }

    public static int eggDropItr(int floors, int eggs, Integer[][] mem) {
        for (int f = 0; f <= floors; f++) {
            for (int e = 1; e <= eggs; e++) {
                if (f < 2 || e == 1) {
                    mem[f][e] = f;
                } else {
                    mem[f][e] = f;
                    for (int i = 1; i <= f; i++) {
                        int floorWorst = 1 + Math.max(mem[i-1][e-1], mem[f-i][e]);
                        if (floorWorst < mem[f][e]) {
                            mem[f][e] = floorWorst;
                        }
                    }
                }
            }
        }
        return mem[floors][eggs];
    }
}

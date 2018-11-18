package pl.edu.agh.macwozni.dmeshparallel;

import pl.edu.agh.macwozni.dmeshparallel.myProductions.*;
import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;
import pl.edu.agh.macwozni.dmeshparallel.mesh.GraphDrawer;
import pl.edu.agh.macwozni.dmeshparallel.parallelism.BlockRunner;
import pl.edu.agh.macwozni.dmeshparallel.production.PDrawer;

public class Executor extends Thread {

    private final BlockRunner runner;

    public Executor(BlockRunner _runner) {
        this.runner = _runner;
    }

    @Override
    public void run() {
        PDrawer drawer = new GraphDrawer();
        int n = 3;
        if (n == 3) {
            Vertex v = new Vertex();
            PW pw = new PW(v, drawer);
            PN pn = new PN(v, drawer);
            PE pe = new PE(v, drawer);
            PS ps = new PS(v, drawer);
            this.runner.addThread(pw);
            this.runner.addThread(pn);
            this.runner.addThread(pe);
            this.runner.addThread(ps);
            this.runner.startAll();
            PN pn2 = new PN(v.getLeft(), drawer);
            PS ps2 = new PS(v.getLeft(), drawer);
            this.runner.addThread(pn2);
            this.runner.addThread(ps2);
            PN pn3 = new PN(v.getRight(), drawer);
            PS ps3 = new PS(v.getRight(), drawer);
            this.runner.addThread(pn3);
            this.runner.addThread(ps3);
            this.runner.startAll();
            //drawer.draw(v);
        } else if (n == 4) {
            Vertex v = new Vertex();
            PW pw = new PW(v, drawer);
            PN pn = new PN(v, drawer);
            PE pe = new PE(v, drawer);
            PS ps = new PS(v, drawer);
            this.runner.addThread(pw);
            this.runner.addThread(pn);
            this.runner.addThread(pe);
            this.runner.addThread(ps);
            this.runner.startAll();
            PN pn2 = new PN(v.getLeft(), drawer);
            PS ps2 = new PS(v.getLeft(), drawer);
            PN pn3 = new PN(v.getRight(), drawer);
            PE pe2 = new PE(v.getRight(), drawer);
            PS ps3 = new PS(v.getRight(), drawer);
            PS ps4 = new PS(v.getBottom(), drawer);
            this.runner.addThread(pn2);
            this.runner.addThread(pn3);
            this.runner.addThread(pe2);
            this.runner.addThread(ps2);
            this.runner.addThread(ps3);
            this.runner.addThread(ps4);
            this.runner.startAll();
            PN pn4 = new PN(v.getRight().getRight(), drawer);
            PS ps5 = new PS(v.getRight().getRight(), drawer);
            PS ps6 = new PS(v.getRight().getBottom(), drawer);
            PS ps7 = new PS(v.getLeft().getBottom(), drawer);
            this.runner.addThread(pn4);
            this.runner.addThread(ps5);
            this.runner.addThread(ps6);
            this.runner.addThread(ps7);
            this.runner.startAll();
            PS ps8 = new PS(v.getRight().getRight().getBottom(), drawer);
            this.runner.addThread(ps8);
            this.runner.startAll();
            //drawer.draw(v);
        }
    }
}
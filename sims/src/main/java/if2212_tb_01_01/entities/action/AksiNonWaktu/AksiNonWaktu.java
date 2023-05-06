package if2212_tb_01_01.entities.action.AksiNonWaktu;
import if2212_tb_01_01.entities.action.Aksi;
import if2212_tb_01_01.entities.sim.Sim;

public abstract class AksiNonWaktu extends Aksi{
    public AksiNonWaktu(Sim sim){
        super(sim, 0);
    }

    public void run(){
        efekToSim();
    }

    public abstract void efekToSim();
}

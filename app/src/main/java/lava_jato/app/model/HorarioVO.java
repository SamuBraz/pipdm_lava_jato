package lava_jato.app.model;

public class HorarioVO {
    private int idHorario;
    private int idClient;
    private String horarioInicio;

    public HorarioVO(){}

    public HorarioVO(int idHorario){
        this.idHorario = idHorario;
    }

    public HorarioVO(int idHorario, int idClient, String horarioInicio, String horarioTermino){
        this.idHorario = idHorario;
        this.idClient= idClient;
        this.horarioInicio = horarioInicio;
    }
    public int getIdHorario(){
        return this.idHorario;
    }

    public void setIdHorario(int idHorario){
        this.idHorario = idHorario;
    }


    public void setIdClient(int idClient){
        this.idClient = idClient;
    }

    public int getIdClient(){
        return idClient;
    }

    public void setHorarioInicio(String horarioInicio){
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioInicio(){
        return horarioInicio;
    }

}

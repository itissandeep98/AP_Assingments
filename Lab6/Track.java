
public class Track {
    private int length_of_track;
    private int no_of_snakes;
    private int no_of_cricket;
    private int no_of_vulture;
    private int no_of_trampoline;
    private Tile track[];

    public Track(int length_of_track,int no_of_snakes,int no_of_cricket,int no_of_vulture,int no_of_trampoline){
        this.length_of_track=length_of_track;
        this.no_of_snakes=no_of_snakes;
        this.no_of_cricket=no_of_cricket;
        this.no_of_vulture=no_of_vulture;
        this.no_of_trampoline=no_of_trampoline;
    }


    public Tile[] getTrack(int i) {
    	return this.track[i];
    }
    
    public Tile[] getTrack() {
        return this.track;
    }

    public void setTrack(Tile track[]) {
    	this.track = track;
    }
    
    public void setTrack(int i,Tile obj) {
        this.track[i] = obj;
    }
    
    public int getLength_of_track() {
        return this.length_of_track;
    }

    public void setLength_of_track(int length_of_track) {
        this.length_of_track = length_of_track;
    }

    public int getNo_of_snakes() {
        return this.no_of_snakes;
    }

    public void setNo_of_snakes(int no_of_snakes) {
        this.no_of_snakes = no_of_snakes;
    }

    public int getNo_of_cricket() {
        return this.no_of_cricket;
    }

    public void setNo_of_cricket(int no_of_cricket) {
        this.no_of_cricket = no_of_cricket;
    }

    public int getNo_of_vulture() {
        return this.no_of_vulture;
    }

    public void setNo_of_vulture(int no_of_vulture) {
        this.no_of_vulture = no_of_vulture;
    }

    public int getNo_of_trampoline() {
        return this.no_of_trampoline;
    }

    public void setNo_of_trampoline(int no_of_trampoline) {
        this.no_of_trampoline = no_of_trampoline;
    }
    
}
package simulator;

import eduni.simjava.*;

class Source extends Sim_entity {
	private Sim_port out;
	private double delay;

	Source(String name, double delay) {
		super(name);
		this.delay = delay;
		out = new Sim_port("Out");
		add_port(out);
	}

	public void body() {
		for (int i = 0; i < 100; i++) {
			sim_schedule(out, 0.0, 0);
			sim_trace(1, "New request arrives in the system.");
			sim_pause(delay);
		}
	}
}
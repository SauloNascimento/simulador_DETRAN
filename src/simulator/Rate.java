package simulator;

import eduni.simjava.*;

class Rate extends Sim_entity {
	private Sim_port in;
	private double delay;

	Rate(String name, double delay) {
		super(name);
		this.delay = delay;
		in = new Sim_port("In");
		add_port(in);
	}

	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_trace(1, "Disk request started");
			sim_process(delay);
			sim_completed(e);
		}
	}
}
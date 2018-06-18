package simulator;

import eduni.simjava.*;

class Fines extends Sim_entity {
	private Sim_port in, out;
	private double delay;

	Fines(String name, double delay) {
		super(name);
		this.delay = delay;
		in = new Sim_port("In");
		out = new Sim_port("Out");
		add_port(in);
		add_port(out);
	}

	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay);
			sim_completed(e);
			sim_trace(1, "Rate selected for I/O work.");
			sim_schedule(out, 0.0, 1);
		}
	}
}
package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Source extends Sim_entity {
	private Sim_port out;
	private Sim_negexp_obj delay;

	Source(String name, double mean) {
		super(name);
		out = new Sim_port("Out");
		add_port(out);
		delay = new Sim_negexp_obj("DelaySource", mean);
        add_generator(delay);
	}

	public void body() {
		while (Sim_system.running()) {
			sim_schedule(out, 0.0, 0);
			sim_trace(1, "New request arrives in the system.");
			sim_pause(delay.sample());
		}
	}
}
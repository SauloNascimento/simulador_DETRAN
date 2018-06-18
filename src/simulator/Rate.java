package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Rate extends Sim_entity {
	private Sim_port in;
	private Sim_normal_obj delay;

	Rate(String name, double mean, double var) {
		super(name);
		in = new Sim_port("In");
		add_port(in);
		this.delay = new Sim_normal_obj("DelayRate", mean, var);
        add_generator(delay);
	}

	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_trace(1, "Disk request started");
			sim_process(delay.sample());
			sim_completed(e);
		}
	}
}
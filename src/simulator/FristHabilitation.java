package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class FristHabilitation extends Sim_entity {
	private Sim_port in, out;
	private Sim_normal_obj delay;

	FristHabilitation(String name, double mean, double var) {
		super(name);
		in = new Sim_port("In");
		out = new Sim_port("Out");
		add_port(in);
		add_port(out);
		this.delay = new Sim_normal_obj("DelayFristHabilitation", mean, var);
        add_generator(delay);
	}

	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay.sample());
			sim_completed(e);
			sim_trace(1, "Following to Rate.");
			sim_schedule(out, 0.0, 1);
		}
	}
}
package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Auction extends Sim_entity {
	private Sim_port in, out1, out2;
	private double delay;
	private Sim_random_obj prob;

	Auction(String name, double delay) {
		super(name);
		in = new Sim_port("In");
		out1 = new Sim_port("Out1");
		out2 = new Sim_port("Out2");
		add_port(in);
		add_port(out1);
		add_port(out2);
		this.delay = delay;
		this.prob = new Sim_random_obj("Auction Probability");
        add_generator(prob);
	}

	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay);
			sim_completed(e);
			double i = prob.sample();
			if (i < 0.42) {
				sim_trace(1, "Rate selected for I/O work.");
				sim_schedule(out1, 0.0, 1);
			} else {
				sim_trace(1, "Fines selected for I/O work.");
				sim_schedule(out2, 0.0, 1);
			}
		}
	}
}
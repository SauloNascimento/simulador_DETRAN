package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Ticket extends Sim_entity {
	private Sim_port in, out1, out2, out3, out4, out5;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;

	Ticket(String name, double mean, double var) {
		super(name);
		in = new Sim_port("In");
		out1 = new Sim_port("Out1");
		out2 = new Sim_port("Out2");
		out3 = new Sim_port("Out3");
		out4 = new Sim_port("Out4");
		out5 = new Sim_port("Out5");
		add_port(in);
		add_port(out1);
		add_port(out2);
		add_port(out3);
		add_port(out4);
		add_port(out5);
		this.delay = new Sim_normal_obj("DelayTicket", mean, var);
		this.prob = new Sim_random_obj("TicketProbability");
        add_generator(prob);
        add_generator(delay);
	}

	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay.sample());;
			sim_completed(e);
			double i = prob.sample();
			if (i < 0.12) {
				sim_trace(1, "Auction selected for attendance.");
				sim_schedule(out1, 0.0, 1);
			} else if (i < 0.46) {
				sim_trace(1, "Habilitation selected for attendance.");
				sim_schedule(out2, 0.0, 1);
			} else if (i < 0.71) {
				sim_trace(1, "Fines selected for attendance.");
				sim_schedule(out3, 0.0, 1);
			} else if (i < 0.86) {
				sim_trace(1, "Vehicles Seizure selected for attendance.");
				sim_schedule(out4, 0.0, 1);
			} else {
				sim_trace(1, "Licensing selected for attendance.");
				sim_schedule(out5, 0.0, 1);
			}
		}
	}
}
package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Ticket extends Sim_entity {
	private Sim_port in, out1, out2, out3, out4, out5;
	private Sim_negexp_obj delay;
	private Sim_random_obj prob;
	private Sim_stat stat;

	Ticket(String name, double mean) {
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
		this.delay = new Sim_negexp_obj("DelayTicket", mean);
		this.prob = new Sim_random_obj("TicketProbability");
		add_generator(prob);
		add_generator(delay);
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.UTILISATION);
		stat.add_measure("Response Time", Sim_stat.INTERVAL_BASED);
		stat.add_measure(Sim_stat.WAITING_TIME);
		stat.add_measure(Sim_stat.QUEUE_LENGTH);
		set_stat(stat);
	}

	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay.sample());
			;
			sim_completed(e);
			sim_trace(1, "ET: " + e.event_time() + " CLK: " + Sim_system.sim_clock());
			stat.update("Response Time", e.event_time(), Sim_system.sim_clock());
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
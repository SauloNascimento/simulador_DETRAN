package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Habilitation extends Sim_entity {
	private Sim_port in, out1, out2;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	private Sim_stat stat;

	Habilitation(String name, double mean, double var) {
		super(name);
		in = new Sim_port("In");
		out1 = new Sim_port("Out1");
		out2 = new Sim_port("Out2");
		add_port(in);
		add_port(out1);
		add_port(out2);
		this.delay = new Sim_normal_obj("DelayHabilitation", mean, var);
		this.prob = new Sim_random_obj("HabilitationProbability");
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
			sim_completed(e);
			stat.update("Response Time", e.event_time(), Sim_system.sim_clock());
			double i = prob.sample();
			if (i < 0.59) {
				sim_trace(1, "Frist Habilitation selected for attendance.");
				sim_schedule(out1, 0.0, 1);
			} else {
				sim_trace(1, "Renovation selected for attendance.");
				sim_schedule(out2, 0.0, 1);
			}
		}
	}
}
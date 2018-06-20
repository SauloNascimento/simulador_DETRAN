package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Fines extends Sim_entity {
	private Sim_port in, out;
	private Sim_normal_obj delay;
	private Sim_stat stat;

	Fines(String name, double mean, double var) {
		super(name);
		in = new Sim_port("In");
		out = new Sim_port("Out");
		add_port(in);
		add_port(out);
		this.delay = new Sim_normal_obj("DelayFines", mean, var);
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
			sim_trace(1, "Following to Rate.");
			sim_schedule(out, 0.0, 1);
		}
	}
}
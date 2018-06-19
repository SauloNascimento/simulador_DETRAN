package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

class Rate extends Sim_entity {
	private Sim_port in;
	private Sim_normal_obj delay;
	private Sim_stat stat;

	Rate(String name, double mean, double var) {
		super(name);
		in = new Sim_port("In");
		add_port(in);
		this.delay = new Sim_normal_obj("DelayRate", mean, var);
        add_generator(delay);
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.UTILISATION);
		stat.add_measure(Sim_stat.WAITING_TIME);
		stat.add_measure(Sim_stat.QUEUE_LENGTH);
		set_stat(stat);
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
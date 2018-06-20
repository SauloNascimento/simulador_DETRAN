package simulator;

import eduni.simjava.*;
import eduni.simjava.distributions.*;

public class DetranSubsystem {

	public static void main(String[] args) {
		Sim_system.initialise();
		Source source = new Source("Source", 0.5);
		Ticket processor = new Ticket("Ticket", 3);
		Auction auction = new Auction("Auction", 50, 15);
		Habilitation habilitation = new Habilitation("Habilitation", 20, 5);
		Fines fines = new Fines("Fines", 20, 10);
		VehiclesSeizure vehiclesSeizure = new VehiclesSeizure("VehiclesSeizure", 10, 2);
		Licensing licensing = new Licensing("Licensing", 30, 10);
		FristHabilitation fristHabilitation = new FristHabilitation("FristHabilitation", 40, 15);
		Renovation renovation = new Renovation("Renovation", 15, 10);
		Rate rate = new Rate("Rate", 30, 10);

		Sim_system.link_ports("Source", "Out", "Ticket", "In");
		Sim_system.link_ports("Ticket", "Out1", "Auction", "In");
		Sim_system.link_ports("Ticket", "Out2", "Habilitation", "In");
		Sim_system.link_ports("Ticket", "Out3", "Fines", "In");
		Sim_system.link_ports("Ticket", "Out4", "VehiclesSeizure", "In");
		Sim_system.link_ports("Ticket", "Out5", "Licensing", "In");
		Sim_system.link_ports("Habilitation", "Out1", "FristHabilitation", "In");
		Sim_system.link_ports("Habilitation", "Out2", "Renovation", "In");
		Sim_system.link_ports("Auction", "Out2", "Fines", "In");
		Sim_system.link_ports("VehiclesSeizure", "Out2", "Fines", "In");
		Sim_system.link_ports("Auction", "Out1", "Rate", "In");
		Sim_system.link_ports("Fines", "Out", "Rate", "In");
		Sim_system.link_ports("VehiclesSeizure", "Out1", "Rate", "In");
		Sim_system.link_ports("Licensing", "Out", "Rate", "In");
		Sim_system.link_ports("FristHabilitation", "Out", "Rate", "In");
		Sim_system.link_ports("Renovation", "Out", "Rate", "In");
		Sim_system.set_transient_condition(Sim_system.TIME_ELAPSED, 1000);
		Sim_system.set_trace_detail(false, true, false);
        Sim_system.set_termination_condition(Sim_system.EVENTS_COMPLETED, "Ticket", 0, 200, false);
		Sim_system.run();
	}
}
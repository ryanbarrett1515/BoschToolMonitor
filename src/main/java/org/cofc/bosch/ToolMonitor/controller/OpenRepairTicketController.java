package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.OpenRepairTicket.OpenRepairTicket;
import org.cofc.bosch.ToolMonitor.components.OpenRepairTicket.OpenRepairTicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OpenRepairTicketController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/createOpenRepairTicket")
    public String openRepairTicketForm(Model model) {
        List<String> valueStreams = jdbcTemplate.queryForList("Select Distinct valueStream From WPCCombos;", String.class);
        List<String> prodLines = null;
        List<String> prodTypes = null;
        List<String> repairDets = null;
        List<String> repairCats = null;
        if (!valueStreams.isEmpty()) {
            prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                    + valueStreams.get(0) + "\";", String.class);
            if (!prodLines.isEmpty()) {
                prodTypes = jdbcTemplate.queryForList("Select Distinct productType From WPCCombos where valueStream=\""
                        + valueStreams.get(0) + "\" and productionLine=\"" + prodLines.get(0) + "\";", String.class);
            }
            if (!prodLines.isEmpty()) {
                repairCats = jdbcTemplate.queryForList("Select Distinct repairCategory From RepairCodes where valueStream=\""
                        + valueStreams.get(0) + "\" and productionLine=\"" + prodLines.get(0) + "\";", String.class);
                if (!repairCats.isEmpty()) {
                    repairDets = jdbcTemplate.queryForList("Select Distinct repairDetail From RepairCodes where valueStream=\""
                            + valueStreams.get(0) + "\" and productionLine=\"" + prodLines.get(0) + "\" and " +
                            "repairCategory=\"" + repairCats.get(0) + "\";", String.class);
                }
            }
        }
        model.addAttribute("valueStreams", valueStreams);
        model.addAttribute("prodLines", prodLines);
        model.addAttribute("prodTypes", prodTypes);
        model.addAttribute("repairCats", repairCats);
        model.addAttribute("repairDets", repairDets);
        model.addAttribute("repairTicket", new OpenRepairTicket());

        return "openRepairTicketForm";
    }

    @PostMapping("/createOpenRepairTicket")
    public String openRepairTicketSubmission(@ModelAttribute OpenRepairTicket repairTicket, Model model) {
        model.addAttribute("repairTicket", repairTicket);
        try {
            repairTicket.enterOpenRepairTicketIntoDatabase(jdbcTemplate);
        } catch (DataAccessException e) {
            model.addAttribute("error", e.getMessage());

            List<String> valueStreams = jdbcTemplate.queryForList("Select Distinct valueStream From WPCCombos;", String.class);
            List<String> prodLines = null;
            List<String> prodTypes = null;
            List<String> repairDets = null;
            List<String> repairCats = null;
            if (!valueStreams.isEmpty()) {
                prodLines = jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\""
                        + repairTicket.getValueStream() + "\";", String.class);
                if (!prodLines.isEmpty()) {
                    prodTypes = jdbcTemplate.queryForList("Select Distinct productType From WPCCombos where valueStream=\""
                            + repairTicket.getValueStream() + "\" and productionLine=\"" + repairTicket.getProductionLine() + "\";", String.class);
                }
                if (!prodLines.isEmpty()) {
                    repairCats = jdbcTemplate.queryForList("Select Distinct repairCategory From RepairCodes where valueStream=\""
                            + repairTicket.getValueStream() + "\" and productionLine=\"" + repairTicket.getProductionLine() + "\";", String.class);
                    if (!repairCats.isEmpty()) {
                        repairDets = jdbcTemplate.queryForList("Select Distinct repairDetail From RepairCodes where valueStream=\""
                                + repairTicket.getValueStream() + "\" and productionLine=\"" + repairTicket.getProductionLine() + "\" and " +
                                "repairCategory=\"" + repairTicket.getRepairCategory() + "\";", String.class);
                    }
                }
            }
            model.addAttribute("valueStreams", valueStreams);
            model.addAttribute("prodLines", prodLines);
            model.addAttribute("prodTypes", prodTypes);
            model.addAttribute("repairCats", repairCats);
            model.addAttribute("repairDets", repairDets);

            return "openRepairTicketForm";
        }
        return "openRepairTicketSubmission";
    }

    @GetMapping("/openedRepairTickets")
    public String openedRepairTickets(Model model) {
        List<OpenRepairTicket> repairTickets = jdbcTemplate.query("Select * From RepairTickets", new OpenRepairTicketMapper());
        model.addAttribute("repairTickets", repairTickets);

        return "openRepairTickets";
    }

    @GetMapping("/delete_repairTicket")
    public String deleteRepairTicket(@RequestParam String valueStream, @RequestParam String productionLine, @RequestParam String productType,
                                     @RequestParam int workPieceCarrierNumber, @RequestParam String repairCategory, @RequestParam String repairDetail,
                                     @RequestParam String userEntry, @RequestParam String timeStampOpened, Model model) {
        OpenRepairTicket.deleteOpenRepairTicket(jdbcTemplate, valueStream, productionLine, productType, workPieceCarrierNumber, repairCategory, repairDetail, userEntry, timeStampOpened);
        model.addAttribute("repairTickets", jdbcTemplate.query("Select * From RepairTickets", new OpenRepairTicketMapper()));

        return "repairTickets";
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

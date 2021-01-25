package com.syswarp.views.timeship;

import java.util.Optional;

import com.syswarp.data.entity.Timeship;
import com.syswarp.data.service.TimeshipService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import com.syswarp.views.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@Route(value = "timeship", layout = MainView.class)
@PageTitle("Timeship")
@CssImport("./styles/views/timeship/timeship-view.css")
public class TimeshipView extends Div {

    private Grid<Timeship> grid = new Grid<>(Timeship.class, false);

    private TextField id;
    private TextField idbuque;
    private TextField idagencia;
    private DatePicker fecha_etp;
    private TextField idempresa;
    private TextField observaciones;
    private TextField idpuertodesde;
    private TextField idmuelledesde;
    private TextField idpuertohasta;
    private TextField idmuellehasta;
    private DatePicker fecha_transaccion;
    private TextField idmaniobra;
    private TextField idestado;
    private DatePicker f_est_inicio_maniobra;
    private DatePicker f_est_fin_maniobra;
    private TextField idpractico;
    private DatePicker f_asignacion;
    private TextField idlancha;
    private TextField idremis;
    private TextField nrodespacho;
    private DatePicker f_fin_maniobra;
    private DatePicker f_desembarco;
    private DatePicker f_presentacion;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Timeship> binder;

    private Timeship timeship;

    public TimeshipView(@Autowired TimeshipService timeshipService) {
        setId("timeship-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("idbuque").setAutoWidth(true);
        grid.addColumn("idagencia").setAutoWidth(true);
        grid.addColumn("fecha_etp").setAutoWidth(true);
        grid.addColumn("idempresa").setAutoWidth(true);
        grid.addColumn("observaciones").setAutoWidth(true);
        grid.addColumn("idpuertodesde").setAutoWidth(true);
        grid.addColumn("idmuelledesde").setAutoWidth(true);
        grid.addColumn("idpuertohasta").setAutoWidth(true);
        grid.addColumn("idmuellehasta").setAutoWidth(true);
        grid.addColumn("fecha_transaccion").setAutoWidth(true);
        grid.addColumn("idmaniobra").setAutoWidth(true);
        grid.addColumn("idestado").setAutoWidth(true);
        grid.addColumn("f_est_inicio_maniobra").setAutoWidth(true);
        grid.addColumn("f_est_fin_maniobra").setAutoWidth(true);
        grid.addColumn("idpractico").setAutoWidth(true);
        grid.addColumn("f_asignacion").setAutoWidth(true);
        grid.addColumn("idlancha").setAutoWidth(true);
        grid.addColumn("idremis").setAutoWidth(true);
        grid.addColumn("nrodespacho").setAutoWidth(true);
        grid.addColumn("f_fin_maniobra").setAutoWidth(true);
        grid.addColumn("f_desembarco").setAutoWidth(true);
        grid.addColumn("f_presentacion").setAutoWidth(true);
        grid.setItems(query -> timeshipService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Timeship> timeshipFromBackend = timeshipService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (timeshipFromBackend.isPresent()) {
                    populateForm(timeshipFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Timeship.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(id).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("id");
        binder.forField(idbuque).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idbuque");
        binder.forField(idagencia).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idagencia");
        binder.forField(idempresa).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idempresa");
        binder.forField(idpuertodesde).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idpuertodesde");
        binder.forField(idmuelledesde).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idmuelledesde");
        binder.forField(idpuertohasta).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idpuertohasta");
        binder.forField(idmuellehasta).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idmuellehasta");
        binder.forField(idmaniobra).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idmaniobra");
        binder.forField(idestado).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idestado");
        binder.forField(idpractico).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idpractico");
        binder.forField(idlancha).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idlancha");
        binder.forField(idremis).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idremis");
        binder.forField(nrodespacho).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("nrodespacho");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.timeship == null) {
                    this.timeship = new Timeship();
                }
                binder.writeBean(this.timeship);

                timeshipService.update(this.timeship);
                clearForm();
                refreshGrid();
                Notification.show("Timeship details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the timeship details.");
            }
        });

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        id = new TextField("Id");
        idbuque = new TextField("Idbuque");
        idagencia = new TextField("Idagencia");
        fecha_etp = new DatePicker("Fecha_etp");
        idempresa = new TextField("Idempresa");
        observaciones = new TextField("Observaciones");
        idpuertodesde = new TextField("Idpuertodesde");
        idmuelledesde = new TextField("Idmuelledesde");
        idpuertohasta = new TextField("Idpuertohasta");
        idmuellehasta = new TextField("Idmuellehasta");
        fecha_transaccion = new DatePicker("Fecha_transaccion");
        idmaniobra = new TextField("Idmaniobra");
        idestado = new TextField("Idestado");
        f_est_inicio_maniobra = new DatePicker("F_est_inicio_maniobra");
        f_est_fin_maniobra = new DatePicker("F_est_fin_maniobra");
        idpractico = new TextField("Idpractico");
        f_asignacion = new DatePicker("F_asignacion");
        idlancha = new TextField("Idlancha");
        idremis = new TextField("Idremis");
        nrodespacho = new TextField("Nrodespacho");
        f_fin_maniobra = new DatePicker("F_fin_maniobra");
        f_desembarco = new DatePicker("F_desembarco");
        f_presentacion = new DatePicker("F_presentacion");
        Component[] fields = new Component[]{id, idbuque, idagencia, fecha_etp, idempresa, observaciones, idpuertodesde,
                idmuelledesde, idpuertohasta, idmuellehasta, fecha_transaccion, idmaniobra, idestado,
                f_est_inicio_maniobra, f_est_fin_maniobra, idpractico, f_asignacion, idlancha, idremis, nrodespacho,
                f_fin_maniobra, f_desembarco, f_presentacion};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Timeship value) {
        this.timeship = value;
        binder.readBean(this.timeship);

    }
}

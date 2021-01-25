package com.syswarp.views.practicos;

import java.util.Optional;

import com.syswarp.data.entity.Practicos;
import com.syswarp.data.service.PracticosService;
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

@Route(value = "practicos", layout = MainView.class)
@PageTitle("Practicos")
@CssImport("./styles/views/practicos/practicos-view.css")
public class PracticosView extends Div {

    private Grid<Practicos> grid = new Grid<>(Practicos.class, false);

    private TextField id;
    private TextField practico;
    private TextField habilitacion;
    private TextField telefono;
    private TextField celular;
    private TextField otro_telefono;
    private TextField direccion;
    private DatePicker f_revisacion_medica;
    private DatePicker f_vencimiento_chaleco;
    private TextField idempresa;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Practicos> binder;

    private Practicos practicos;

    public PracticosView(@Autowired PracticosService practicosService) {
        setId("practicos-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("practico").setAutoWidth(true);
        grid.addColumn("habilitacion").setAutoWidth(true);
        grid.addColumn("telefono").setAutoWidth(true);
        grid.addColumn("celular").setAutoWidth(true);
        grid.addColumn("otro_telefono").setAutoWidth(true);
        grid.addColumn("direccion").setAutoWidth(true);
        grid.addColumn("f_revisacion_medica").setAutoWidth(true);
        grid.addColumn("f_vencimiento_chaleco").setAutoWidth(true);
        grid.addColumn("idempresa").setAutoWidth(true);
        grid.setItems(query -> practicosService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Practicos> practicosFromBackend = practicosService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (practicosFromBackend.isPresent()) {
                    populateForm(practicosFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Practicos.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(id).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("id");
        binder.forField(idempresa).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idempresa");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.practicos == null) {
                    this.practicos = new Practicos();
                }
                binder.writeBean(this.practicos);

                practicosService.update(this.practicos);
                clearForm();
                refreshGrid();
                Notification.show("Practicos details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the practicos details.");
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
        practico = new TextField("Practico");
        habilitacion = new TextField("Habilitacion");
        telefono = new TextField("Telefono");
        celular = new TextField("Celular");
        otro_telefono = new TextField("Otro_telefono");
        direccion = new TextField("Direccion");
        f_revisacion_medica = new DatePicker("F_revisacion_medica");
        f_vencimiento_chaleco = new DatePicker("F_vencimiento_chaleco");
        idempresa = new TextField("Idempresa");
        Component[] fields = new Component[]{id, practico, habilitacion, telefono, celular, otro_telefono, direccion,
                f_revisacion_medica, f_vencimiento_chaleco, idempresa};

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

    private void populateForm(Practicos value) {
        this.practicos = value;
        binder.readBean(this.practicos);

    }
}

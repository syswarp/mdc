package com.syswarp.views.puertos;

import java.util.Optional;

import com.syswarp.data.entity.Puertos;
import com.syswarp.data.service.PuertosService;
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
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@Route(value = "puertos", layout = MainView.class)
@PageTitle("Puertos")
@CssImport("./styles/views/puertos/puertos-view.css")
public class PuertosView extends Div {

    private Grid<Puertos> grid = new Grid<>(Puertos.class, false);

    private TextField id;
    private TextField puerto;
    private TextField adicional;
    private TextField bonificacion;
    private TextField interno;
    private TextField latitud;
    private TextField longitud;
    private TextField domicilio;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Puertos> binder;

    private Puertos puertos;

    public PuertosView(@Autowired PuertosService puertosService) {
        setId("puertos-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("puerto").setAutoWidth(true);
        grid.addColumn("adicional").setAutoWidth(true);
        grid.addColumn("bonificacion").setAutoWidth(true);
        grid.addColumn("interno").setAutoWidth(true);
        grid.addColumn("latitud").setAutoWidth(true);
        grid.addColumn("longitud").setAutoWidth(true);
        grid.addColumn("domicilio").setAutoWidth(true);
        grid.setItems(query -> puertosService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Puertos> puertosFromBackend = puertosService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (puertosFromBackend.isPresent()) {
                    populateForm(puertosFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Puertos.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(id).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("id");
        binder.forField(adicional).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("adicional");
        binder.forField(bonificacion).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("bonificacion");
        binder.forField(interno).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("interno");
        binder.forField(latitud).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("latitud");
        binder.forField(longitud).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("longitud");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.puertos == null) {
                    this.puertos = new Puertos();
                }
                binder.writeBean(this.puertos);

                puertosService.update(this.puertos);
                clearForm();
                refreshGrid();
                Notification.show("Puertos details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the puertos details.");
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
        puerto = new TextField("Puerto");
        adicional = new TextField("Adicional");
        bonificacion = new TextField("Bonificacion");
        interno = new TextField("Interno");
        latitud = new TextField("Latitud");
        longitud = new TextField("Longitud");
        domicilio = new TextField("Domicilio");
        Component[] fields = new Component[]{id, puerto, adicional, bonificacion, interno, latitud, longitud,
                domicilio};

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

    private void populateForm(Puertos value) {
        this.puertos = value;
        binder.readBean(this.puertos);

    }
}
